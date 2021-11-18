package be.parkalot.knight_parkalot.api;

import be.parkalot.knight_parkalot.dto.CreateMemberDto;
import be.parkalot.knight_parkalot.dto.MemberDto;
import be.parkalot.knight_parkalot.dto.RetrieveMemberDto;
import be.parkalot.knight_parkalot.service.MemberService;
import be.parkalot.knight_parkalot.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/members")
public class MemberController {


    private final MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto registerMember(@RequestBody CreateMemberDto createMemberDto) {
        logger.info("registerMember() called");
        return memberService.registerMember(createMemberDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public List<RetrieveMemberDto> getAllMembers() {
        logger.info("getAllMembers() called");
        return memberService.getAllMembers();
    }

    @GetMapping(path = "/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MANAGER)
    public MemberDto getMember(@PathVariable int memberId) {
        logger.info("getMember() called");
        return memberService.getMemberDtoById(memberId);
    }

    @PatchMapping(path = "/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.MEMBER)
    public MemberDto changeMembershipLevel(@PathVariable int memberId, @RequestParam int newMembershipLevelId) {
        logger.info("changeMembershipLevel() called");
        return memberService.changeMembershipLevel(memberId, newMembershipLevelId);
    }
}
