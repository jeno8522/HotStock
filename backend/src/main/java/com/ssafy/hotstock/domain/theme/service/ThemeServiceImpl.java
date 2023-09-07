package com.ssafy.hotstock.domain.theme.service;

import com.ssafy.hotstock.domain.theme.domain.Theme;

import com.ssafy.hotstock.domain.theme.domain.ThemeRepository;
import com.ssafy.hotstock.domain.theme.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;


    public Theme insertTheme(Theme theme) {
        return themeRepository.save(theme);
    }


    public Optional<Theme> getThemeById(Long id) {
        return themeRepository.findById(id);
    }


    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Theme updateTheme(Long id, Theme updatedTheme) {
        return themeRepository.findById(id).map(theme -> {
            theme.setName(updatedTheme.getName());
            theme.setKeywordThemes(updatedTheme.getKeywordThemes());
            theme.setStocks(updatedTheme.getStocks());
            return themeRepository.save(theme);
        }).orElseThrow(() -> new ResourceNotFoundException("Theme with id " + id + " not found"));
    }

    // Delete a Theme by ID
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
