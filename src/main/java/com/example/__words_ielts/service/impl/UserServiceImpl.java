package com.example.__words_ielts.service.impl;

import com.example.__words_ielts.enums.ErrorCodeEnum;
import com.example.__words_ielts.enums.GameLevelEnum;
import com.example.__words_ielts.enums.UserRoleEnum;
import com.example.__words_ielts.enums.UserStatusEnum;
import com.example.__words_ielts.exception.AppException;
import com.example.__words_ielts.model.dto.request.user.*;
import com.example.__words_ielts.model.dto.response.user.IntrospectResponse;
import com.example.__words_ielts.model.dto.response.user.LoginResponse;
import com.example.__words_ielts.model.dto.response.user.UserResponse;
import com.example.__words_ielts.model.entity.InvalidatedToken;
import com.example.__words_ielts.model.entity.Topic;
import com.example.__words_ielts.model.entity.User;
import com.example.__words_ielts.model.entity.UserLearnedCount;
import com.example.__words_ielts.repository.InvalidatedRepository;
import com.example.__words_ielts.repository.TopicRepository;
import com.example.__words_ielts.repository.UserLearnedCountRepository;
import com.example.__words_ielts.repository.UserRepository;
import com.example.__words_ielts.service.TopicService;
import com.example.__words_ielts.service.UserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final InvalidatedRepository invalidatedRepository;
    private final TopicRepository topicRepository;
    private final UserLearnedCountRepository userLearnedCountRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @Override
    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (userRepository.existsByEmail(userCreateRequest.getEmail())){
            throw new AppException(ErrorCodeEnum.EMAIL_EXISTED);
        }

        User newUser = User.builder()
                .email(userCreateRequest.getEmail())
                .hashPassword(passwordEncoder.encode(userCreateRequest.getPassword()))
                .status(UserStatusEnum.ACTIVE)
                .role(UserRoleEnum.USER)
                .build();

        newUser = userRepository.save(newUser);

        List<Topic> topics = topicRepository.findAll();

        for (Topic t : topics){
            UserLearnedCount newUserLearnedCount = UserLearnedCount.builder()
                    .user(newUser)
                    .topic(t)
                    .learnedWordCount(0)
                    .gameImage(GameLevelEnum.ZERO)
                    .gameListen(GameLevelEnum.ZERO)
                    .gameListenAndWrite(GameLevelEnum.ZERO)
                    .gameSpeak(GameLevelEnum.ZERO)
                    .gameWord(GameLevelEnum.ZERO)
                    .build();

            userLearnedCountRepository.save(newUserLearnedCount);
        }

        return UserResponse.fromUser(newUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') ")
    public Page<UserResponse> getAllUsers(Pageable pageable, String sort, String direction) {
        log.info("In method get Users");

        Sort sortOrder = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOrder);

        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserResponse::fromUser);
    }

    @Override
    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new AppException(ErrorCodeEnum.EMAIL_NOT_EXISTED));

        return UserResponse.fromUser(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCodeEnum.EMAIL_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(loginRequest.getPassword(), user.getHashPassword());

        if (!authenticated){
            throw new AppException(ErrorCodeEnum.PASSWORD_INCORRECT);
        }

        var token = generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .build();

            invalidatedRepository.save(invalidatedToken);
        } catch (AppException exception){
            log.info("Token already expired");
        }
    }

    @Override
    public UserResponse changePassword(ChangePasswordRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new AppException(ErrorCodeEnum.EMAIL_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getOldPassword(), user.getHashPassword());

        if (!authenticated){
            throw new AppException(ErrorCodeEnum.PASSWORD_INCORRECT);
        }

        user.setHashPassword(passwordEncoder.encode(request.getNewPassword()));

        return UserResponse.fromUser(userRepository.save(user));
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public LoginResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signJWT = verifyToken(request.getToken(), true);

        var jit = signJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidatedRepository.save(invalidatedToken);

        var email = signJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByEmail(email).orElseThrow(
                ()-> new AppException(ErrorCodeEnum.UNAUTHENTICATED)
        );

        var token = generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                        ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                        : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCodeEnum.UNAUTHENTICATED);
        }

        if (invalidatedRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new AppException(ErrorCodeEnum.UNAUTHENTICATED);
        }


        return signedJWT;
    }

    private String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Không thể tạo token", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        if (user.getRole() != null) {
            return user.getRole().name();
        }
        return "";
    }
}
