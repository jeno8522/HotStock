package com.ssafy.hotstock.domain.theme.service;

import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService{
    private final ThemeRepository themeRepository;


    @Override
    public Theme save(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Optional<Theme> findById(Long id) {
        return themeRepository.findById(id);
    }

    @Override
    public List<Theme> findAll() {
        return themeRepository.findAll();
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
