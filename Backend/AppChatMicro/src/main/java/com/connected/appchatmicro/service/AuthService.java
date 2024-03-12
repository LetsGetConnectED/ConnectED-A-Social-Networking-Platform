/*
 * package com.connected.appchatmicro.service;
 * 
 * import com.connected.appchatmicro.dto.*; import
 * com.connected.appchatmicro.exceptions.SpringChatException; import
 * com.connected.appchatmicro.model.Personalinfo; import
 * com.connected.appchatmicro.model.User; import
 * com.connected.appchatmicro.model.VerificationToken; import
 * com.connected.appchatmicro.repo.PersonalinfoRepository; import
 * com.connected.appchatmicro.repo.UserRepository; import
 * com.connected.appchatmicro.repo.VerificationTokenRepository;
 * 
 * import
 * org.springframework.security.authentication.AnonymousAuthenticationToken;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Service; import
 * com.connected.appchatmicro.security.JwtProvider; import
 * org.springframework.transaction.annotation.Transactional; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import java.time.Instant; import java.util.Optional; import java.util.UUID;
 * 
 * @Service
 * 
 * @Transactional public class AuthService {
 * 
 * private final AuthenticationManager authenticationManager; private final
 * PasswordEncoder passwordEncoder; private final ImageStoreService
 * imageStoreService; private final UserRepository userRepository; private final
 * VerificationTokenRepository verificationTokenRepository; private final
 * JwtProvider jwtProvider;
 * 
 * private final RefreshTokenService refreshTokenService; private final
 * PersonalinfoRepository personalInfoRepository;
 * 
 * public AuthService() { super(); // TODO Auto-generated constructor stub }
 * 
 * @Override public String toString() { return
 * "AuthService [authenticationManager=" + authenticationManager +
 * ", passwordEncoder=" + passwordEncoder + ", imageStoreService=" +
 * imageStoreService + ", userRepository=" + userRepository +
 * ", verificationTokenRepository=" + verificationTokenRepository +
 * ", jwtProvider=" + jwtProvider + ", refreshTokenService=" +
 * refreshTokenService + ", personalInfoRepository=" + personalInfoRepository +
 * "]"; }
 * 
 * public AuthenticationManager getAuthenticationManager() { return
 * authenticationManager; }
 * 
 * public PasswordEncoder getPasswordEncoder() { return passwordEncoder; }
 * 
 * public ImageStoreService getImageStoreService() { return imageStoreService; }
 * 
 * public UserRepository getUserRepository() { return userRepository; }
 * 
 * public VerificationTokenRepository getVerificationTokenRepository() { return
 * verificationTokenRepository; }
 * 
 * public JwtProvider getJwtProvider() { return jwtProvider; }
 * 
 * public RefreshTokenService getRefreshTokenService() { return
 * refreshTokenService; }
 * 
 * public PersonalinfoRepository getPersonalInfoRepository() { return
 * personalInfoRepository; }
 * 
 * @Transactional public void signup(RegisterRequest registerRequest) throws
 * Exception {
 * 
 * User user = new User(); user.setUsername(registerRequest.getUsername());
 * user.setEmail(registerRequest.getEmail());
 * user.setCompany_name(registerRequest.getCompany_name());
 * user.setPhone(registerRequest.getPhone());
 * user.setFirst_name(registerRequest.getFirst_name());
 * user.setLast_name(registerRequest.getLast_name());
 * user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
 * 
 * String profile_img = null;
 * 
 * 
 * user.setProfile_picture(profile_img);
 * 
 * user.setCreated(Instant.now()); user.setEnabled(false); // false
 * 
 * 
 * // user.personalinfo = new Personalinfo(); userRepository.save(user); User u
 * = userRepository.findByUserId(user.getUserId()) .orElseThrow(() -> new
 * SpringChatException("Invalid Token")); personalInfoRepository.save(new
 * Personalinfo(u));
 * 
 * 
 * String token = generateVerificationToken(user); verifyAccount(token); //
 * verify directly }
 * 
 * @Transactional public void changeInfo(ChangeInfoRequest changeInfoRequest) {
 * 
 * Optional<User> existing =
 * this.userRepository.findByUsername(changeInfoRequest.getUsername()); User
 * user1 = existing.orElseThrow(() -> new UsernameNotFoundException( "No user "
 * + "Found with username : " + changeInfoRequest.getUsername()));
 * 
 * if (changeInfoRequest.getEmail() != null &&
 * !changeInfoRequest.getEmail().isEmpty())
 * user1.setEmail(changeInfoRequest.getEmail()); if
 * (changeInfoRequest.getPassword() != null &&
 * !changeInfoRequest.getPassword().isEmpty())
 * user1.setPassword(passwordEncoder.encode(changeInfoRequest.getPassword()));
 * 
 * userRepository.save(user1);
 * 
 * }
 * 
 * @Transactional public int changeEmail(ChangeInfoRequest changeInfoRequest) {
 * // returns -1 if email already exists Optional<User> existing =
 * this.userRepository.findByUsername(changeInfoRequest.getUsername()); User
 * user1 = existing .orElseThrow(() -> new UsernameNotFoundException("No user "
 * + "Found with username : " + changeInfoRequest.getUsername()));
 * Optional<User> u = userRepository.findByEmail(changeInfoRequest.getEmail());
 * if (u.isPresent()) return -1; // unable to change the email, because the new
 * one already exists
 * 
 * if(changeInfoRequest.getEmail() != null &&
 * !changeInfoRequest.getEmail().isEmpty())
 * user1.setEmail(changeInfoRequest.getEmail()); userRepository.save(user1);
 * return 0; }
 * 
 * @Transactional public void changePassword(ChangeInfoRequest
 * changeInfoRequest) { Optional<User> existing =
 * this.userRepository.findByUsername(changeInfoRequest.getUsername()); User
 * user1 = existing.orElseThrow(() -> new UsernameNotFoundException( "No user "
 * + "Found with username : " + changeInfoRequest.getUsername())); if
 * (changeInfoRequest.getPassword() != null &&
 * !changeInfoRequest.getPassword().isEmpty())
 * user1.setPassword(passwordEncoder.encode(changeInfoRequest.getPassword()));
 * userRepository.save(user1); }
 * 
 * private String generateVerificationToken(User user) { String token =
 * UUID.randomUUID().toString(); VerificationToken verificationToken = new
 * VerificationToken(); verificationToken.setToken(token);
 * verificationToken.setUser(user);
 * 
 * verificationTokenRepository.save(verificationToken);
 * 
 * return token;
 * 
 * }
 * 
 * public void verifyAccount(String token) throws Exception {
 * Optional<VerificationToken> verificationToken =
 * verificationTokenRepository.findByToken(token);
 * fetchUserAndEnable(verificationToken.orElseThrow(() -> new
 * SpringChatException("Invalid Token"))); //
 * fetchUserAndEnable(verificationToken.get()); }
 * 
 * @Transactional public User getCurrentUser() {
 * org.springframework.security.core.userdetails.User principal =
 * (org.springframework.security.core.userdetails.User) SecurityContextHolder
 * .getContext().getAuthentication().getPrincipal(); return
 * userRepository.findByUsername(principal.getUsername()) .orElseThrow(() -> new
 * UsernameNotFoundException("User name not found - " +
 * principal.getUsername())); }
 * 
 * @Transactional public void fetchUserAndEnable(VerificationToken
 * verificationToken) { String username =
 * verificationToken.getUser().getUsername(); User user =
 * userRepository.findByUsername(username) .orElseThrow(() -> new
 * SpringChatException("User not found with name - " + username));
 * user.setEnabled(true); userRepository.save(user); }
 * 
 * public AuthenticationResponse login(LoginRequest loginRequest) {
 * Authentication authenticate = authenticationManager.authenticate( new
 * UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
 * loginRequest.getPassword()));
 * SecurityContextHolder.getContext().setAuthentication(authenticate);
 * 
 * String token = jwtProvider.generateToken(authenticate); // return new
 * AuthenticationResponse(token, loginRequest.getUsername()); return
 * AuthenticationResponse.builder().authenticationToken(token)
 * .refreshToken(refreshTokenService.generateRefreshToken().getToken())
 * .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
 * .username(loginRequest.getUsername()).build(); }
 * 
 * public AuthenticationResponse refreshToken(RefreshTokenRequest
 * refreshTokenRequest) {
 * refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken(
 * )); String token =
 * jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
 * return AuthenticationResponse.builder().authenticationToken(token)
 * .refreshToken(refreshTokenRequest.getRefreshToken())
 * .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
 * .username(refreshTokenRequest.getUsername()).build(); }
 * 
 * public boolean isLoggedIn() { Authentication authentication =
 * SecurityContextHolder.getContext().getAuthentication(); return
 * !(authentication instanceof AnonymousAuthenticationToken) &&
 * authentication.isAuthenticated(); } }
 */