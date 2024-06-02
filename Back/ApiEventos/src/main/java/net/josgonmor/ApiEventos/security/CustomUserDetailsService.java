package net.josgonmor.ApiEventos.security;

import net.josgonmor.ApiEventos.domain.Post;
import net.josgonmor.ApiEventos.domain.Role;
import net.josgonmor.ApiEventos.domain.Usuario;
import net.josgonmor.ApiEventos.repositories.PostRepository;
import net.josgonmor.ApiEventos.repositories.RoleRepository;
import net.josgonmor.ApiEventos.repositories.UsuarioRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepository,
                                    RoleRepository roleRepository,
                                    PostRepository postRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.postRepository = postRepository;
    }


    public Collection<GrantedAuthority> mapToAutorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//        try {
//            Role role = roleRepository.findById(usuario.getId()).orElseThrow(() -> new RoleNotFoundException("No se ha encontrado el ROl"));
//            usuario.setIdRole(role);
//        } catch (RoleNotFoundException e) {
//            throw new UsernameNotFoundException("No se ha encotnrado el rol");
//        }
//        try {
//            Set<Post> posts = postRepository.findByIdUsuario(usuario).orElseThrow(() -> new Exception("Fallo al cargar los post"));
//            usuario.setPosts(posts);
//        } catch (Exception e){
//            throw new UsernameNotFoundException("Fallo al cargar los post");
//        }
//        Hibernate.initialize(usuario.getIdRole());
//        Hibernate.initialize(usuario.getPosts());
        return new User(usuario.getUsuario(), usuario.getPassword(), mapToAutorities(List.of(usuario.getRole())));
    }
}
