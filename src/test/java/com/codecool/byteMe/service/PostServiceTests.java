package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.UserModel;
import com.codecool.byteMe.model.postable.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PostServiceTests {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    private PostService postService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Post postOne;
    private Post postTwo;
    private UserModel user;

    @BeforeEach
    void setupService() {
        postService = new PostService(postRepository, userRepository);

        user = UserModel.builder()
                .age(18)
                .email("test@byte.me")
                .name("Test User")
                .password(passwordEncoder.encode("test"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .build();

        postOne = Post.builder()
                .id(3L)
                .title("Test Post Title 1")
                .body("Test post body 1")
                .user(user)
                .created(LocalDateTime.of(2022, 1, 1, 16, 52, 12))
                .build();

        postTwo = Post.builder()
                .id(4L)
                .title("Test Post Title 2")
                .body("Test post body 2")
                .user(user)
                .created(LocalDateTime.of(2022, 8, 12, 16, 52, 12))
                .build();
    }

    @DisplayName("JUnit test for getAllPosts method")
    @Test
    public void givenPostList_whenGetAllPosts_thenReturnPostList() {
        // given
        given(postRepository.findAll()).willReturn(List.of(postOne, postTwo));
        // when
        List<Post> postList = postService.getAllPosts();
        // then
        assertThat(postList).isNotNull();
        assertThat(postList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for addPost method")
    @Test
    public void givenPostObject_whenSavePost_thenReturnPostObject() {
        // given
        given(postRepository.save(postOne)).willReturn(postOne);
        // when
        Post savedPost = postService.addPost(postOne);
        // then
        assertThat(savedPost).isNotNull();
        assertEquals(savedPost.getTitle(), "Test Post Title 1");
    }

    @DisplayName("JUnit test for getPostById method")
    @Test
    public void givenPostId_whenGetPostById_thenReturnPostObject() {
        // given
        given(postRepository.findById(3L)).willReturn(Optional.of(postOne));
        // when
        Post savedPost = postService.findById(postOne.getId());
        // then
        assertThat(savedPost).isNotNull();
        assertEquals(savedPost.getTitle(), "Test Post Title 1");
    }
}
