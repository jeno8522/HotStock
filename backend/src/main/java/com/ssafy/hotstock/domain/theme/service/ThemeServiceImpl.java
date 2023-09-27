package com.ssafy.hotstock.domain.theme.service;

import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.dto.ThemeResponseDto;
import com.ssafy.hotstock.domain.theme.repository.ThemeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ThemeServiceImpl implements ThemeService{
    private final ThemeRepository themeRepository;


    @Override
    public Theme insertTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Optional<Theme> findById(Long id) {
        return themeRepository.findById(id);
    }

    @Override
    public List<ThemeResponseDto> getAllThemes() {
        List<Theme> themes =  themeRepository.findAll();
        List<ThemeResponseDto> themeResponseDtos = new ArrayList<>();
        for (Theme theme: themes
             ) {
            ThemeResponseDto themeResponseDto = ThemeResponseDto.builder()
                    .themeId(theme.getId())
                    .name(theme.getName())
                    .build();
            themeResponseDtos.add(themeResponseDto);
        }
        return themeResponseDtos;
    }

    @Override
    public void delete(Theme theme) {
        themeRepository.delete(theme);
    }

    @Override
    public Theme findThemeByName(String name) {
        return themeRepository.findByName(name);
    }
}
