package com.quix.quix.test.match;

import com.quix.quix.match.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntryServiceTest {

    @Mock
    private EntryRepository entryRepository;
    @InjectMocks
    private EntryService entryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getInts() {
        String tickString = "1,2,4 , 6,10";
        int[] ticks = entryService.getInts(tickString);
        int[] expected = {1,2,4,6,10};
        assertThat(ticks).isEqualTo(expected);
    }

    @Test
    void getColorEntry() {
        EntryEntity entry = new EntryEntity();
        entry.setWrongThrow(0);
        entry.setBlue("1");
        entry.setGreen("2, 7");
        entry.setYellow("3, 4");
        entry.setRed("4, 6");

        assertThat(entryService.getColorEntry(Colors.B, entry)).isEqualTo("1");
        assertThat(entryService.getColorEntry(Colors.G, entry)).isEqualTo("2, 7");
        assertThat(entryService.getColorEntry(Colors.Y, entry)).isEqualTo("3, 4");
        assertThat(entryService.getColorEntry(Colors.R, entry)).isEqualTo("4, 6");
    }

    @Test
    void addTick() {

    }


    @Test
    void tickEntry() {
        EntryEntity entry = new EntryEntity();
        entry.setWrongThrow(0);
        when(entryRepository.save(any(EntryEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TickDto tickWrong = new TickDto(true, null, 0);
        assertThat(entryService.tickEntry(tickWrong, entry).getWrongThrow()).isEqualTo(1);

        TickDto tick = new TickDto(false, Colors.B, 1);
        assertThat(entryService.tickEntry(tick, entry).getBlue().trim()).isEqualTo("1");
        tick = new TickDto(false, Colors.G, 1);
        assertThat(entryService.tickEntry(tick, entry).getGreen().trim()).isEqualTo("1");
        tick = new TickDto(false, Colors.Y, 4);
        assertThat(entryService.tickEntry(tick, entry).getYellow().trim()).isEqualTo("4");

        tick = new TickDto(false, Colors.Y, 6);
        assertThat(entryService.tickEntry(tick, entry).getYellow().trim()).isEqualTo("4,6");
        verify(entryRepository, times(5)).save(any(EntryEntity.class));

    }

}