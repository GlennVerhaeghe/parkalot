package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.MembershipLevel;
import be.parkalot.knight_parkalot.dto.*;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;
import be.parkalot.knight_parkalot.mapper.LicensePlateMapper;
import be.parkalot.knight_parkalot.mapper.MemberMapper;
import be.parkalot.knight_parkalot.mapper.PostalCodeMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.repository.MembershipLevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Sql("MemberServiceSetup.sql")
class MemberServiceTest {

    private MemberService memberService;
    //@Autowired
    private MemberRepository mockMemberRepository;

    @Autowired
    private MembershipLevelRepository mockMembershipLevelRepository;
    private LicensePlateMapper licensePlateMapper;
    private PostalCodeService postalCodeService;
    private PostalCodeMapper postalCodeMapper;
    private MemberMapper memberMapper;

    @BeforeEach
    void setUp() {
        mockMemberRepository = Mockito.mock(MemberRepository.class);
        //mockMembershipLevelRepository = Mockito.mock(MembershipLevelRepository.class);
        licensePlateMapper = new LicensePlateMapper();
        memberMapper = Mockito.mock(MemberMapper.class);
        postalCodeService = Mockito.mock(PostalCodeService.class);
        postalCodeMapper = new PostalCodeMapper();

        memberService = new MemberService(mockMemberRepository, memberMapper, mockMembershipLevelRepository, licensePlateMapper, postalCodeService);
    }

    @Test
    void addNewMember_whenAllInfoIsCorrect_ThenSaveToRepo() {
        //given
        CreateMemberDto testDto = new CreateMemberDto(new NameDto("Jean", "Paul"),
                new AddressDto("Beerstraat", "69", new PostalCodeDto(1000, "Brussel")),
                "0469696969",
                "69@soixanteneuf.be",
                new LicensePlateDto("1-ABC-985", "FR"),
                1);
        //when
        memberService.registerMember(testDto);
        //then
        Mockito.verify(mockMemberRepository, Mockito.times(1)).
                save(memberMapper.toEntity(testDto, new MembershipLevel("Bronze", 0, 0, 4), postalCodeMapper.toEntity(testDto.getAddressDto().getPostalCodeDto())));
    }

    @Test
    void addNewMember_whenMissingLastName_throwsException() {
        //given
        CreateMemberDto testDto = new CreateMemberDto(new NameDto("Test", null),
                new AddressDto("Beerstraat", "69", new PostalCodeDto(1000, "Brussel")),
                "0469696969",
                "69@soixanteneuf.be",
                new LicensePlateDto("1-ABC-985", "FR"),
                1);
        //then
        assertThrows(MissingArgumentsException.class, () -> memberService.registerMember(testDto));
    }

    @Test
    void addNewMember_whenMissingPostalCode_throwsException() {
        //given
        CreateMemberDto testDto = new CreateMemberDto(new NameDto(null, "Paul"),
                new AddressDto("Beerstraat", "69", null),
                "0469696969",
                "69@soixanteneuf.be",
                new LicensePlateDto("1-ABC-985", "FR"),
                1);
        //then
        assertThrows(MissingArgumentsException.class, () -> memberService.registerMember(testDto));
    }

  /*  @Test
    void addNewMember_whenMailAlreadyExists_throwsException() {
        //given
        CreateMemberDto testDto = new CreateMemberDto(new CreateNameDto("Jean", "Paul"),
                new AddressDto("Beerstraat", "69", new PostalCodeDto(1000, "Brussel")),
                "0469696969",
                "69@soixanteneu.be",
                new LicensePlateDto("1-ABC-985", "FR"),
                1);
        Mockito.when(memberService.registerMember(testDto)).thenThrow(NotUniqueException.class);
        //then
        assertThrows(NotUniqueException.class, () -> memberService.registerMember(testDto));
    }*/

}