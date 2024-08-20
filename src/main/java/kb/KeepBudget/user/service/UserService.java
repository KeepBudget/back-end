package kb.KeepBudget.user.service;

import kb.KeepBudget.district.repository.DistrictRepository;
import kb.KeepBudget.user.dto.req.NicknameReqDto;
import kb.KeepBudget.user.dto.req.SignUpReqDto;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.user.repository.UserRepository;
import kb.KeepBudget.utils.ApiUtils;
import kb.KeepBudget.utils.exceptions.DistrictNotExistException;
import kb.KeepBudget.utils.exceptions.DuplicateNicknameException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DistrictRepository districtRepository;

    public User signUp(SignUpReqDto reqDto) {
        boolean existsNickname = existsByNickname(reqDto.getNickname());
        if(existsNickname){
            throw new DuplicateNicknameException();
        }
        boolean existsDistrict = districtRepository.existsById(reqDto.getWishDistrictId());
        if(!existsDistrict){
            throw new DistrictNotExistException();
        }
        User user = reqDto.toUser();
        return userRepository.save(user);
    }

    public boolean existsByNickname(String nickname){
        return userRepository.existsByNickname(nickname);
    }

    public User login(NicknameReqDto reqDto) {
        String nickname = reqDto.getNickname();
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NoSuchElementException("해당 닉네임을 가진 사용자가 존재하지 않습니다."));
    }
}
