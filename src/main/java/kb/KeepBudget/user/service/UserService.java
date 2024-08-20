package kb.KeepBudget.user.service;

import kb.KeepBudget.district.repository.DistrictRepository;
import kb.KeepBudget.user.dto.req.NicknameReqDto;
import kb.KeepBudget.user.dto.req.UserReqDto;
import kb.KeepBudget.user.dto.req.WishDistrictReqDto;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.user.repository.UserRepository;
import kb.KeepBudget.utils.exceptions.DistrictNotExistException;
import kb.KeepBudget.utils.exceptions.DuplicateNicknameException;
import kb.KeepBudget.utils.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final DistrictRepository districtRepository;
    private final JWTUtil jwtUtil;

    public String signUp(UserReqDto reqDto) {
        boolean existsNickname = existsByNickname(reqDto.getNickname());
        if(existsNickname){
            throw new DuplicateNicknameException();
        }
        boolean existsDistrict = districtRepository.existsById(reqDto.getWishDistrictId());
        if(!existsDistrict){
            throw new DistrictNotExistException();
        }
        User user = reqDto.toUser();
        User savedUser = userRepository.save(user);
        Long savedUserId = savedUser.getId();
        return createToken(savedUserId);
    }

    public String login(NicknameReqDto reqDto) {
        String nickname = reqDto.getNickname();
        User foundUser = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NoSuchElementException("해당 닉네임을 가진 사용자가 존재하지 않습니다."));
        Long userId = foundUser.getId();
        return createToken(userId);
    }

    public boolean existsByNickname(String nickname){
        return userRepository.existsByNickname(nickname);
    }

    public String createToken(Long userId){
        String userToken = jwtUtil.createJwt(userId, 1000 * 60 * 60L);
        return "Bearer " + userToken;
    }

    public User getUser(String token) {
        String cleanedToken = token.replace("Bearer ", "");
        Long userId = jwtUtil.getId(cleanedToken);
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("해당 요청에 해당하는 사용자가 존재하지 않습니다."));
    }

    public User updateWishDistrict(String token, WishDistrictReqDto reqDto) {
        User user = getUser(token);
        user.setWishDistrictId(reqDto.getWishDistrictId());
        return user;
    }
}
