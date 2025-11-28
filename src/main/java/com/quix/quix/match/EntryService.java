package com.quix.quix.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public int[] getInts(String ticks) {
        if (ticks == null || ticks.isBlank()) {
            return new int[0];
        }
        return Arrays.stream(ticks.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
    }

    public String getColorEntry(Colors color, EntryEntity entry) {
        return switch (color) {
            case B -> entry.getBlue();
            case G -> entry.getGreen();
            case R -> entry.getRed();
            case Y -> entry.getYellow();
        };
    }

    private EntryEntity persistTick(EntryEntity entry, String ticks, Colors color) {
        switch (color) {
            case B -> entry.setBlue(ticks);
            case G -> entry.setGreen(ticks);
            case R -> entry.setRed(ticks);
            case Y -> entry.setYellow(ticks);
        }
        return entryRepository.save(entry);
    }

    private String addTick(String tick, int number) {
        List<Integer> intList = new ArrayList<>(Arrays.stream(getInts(tick)).boxed().toList());
        intList.add(number);
        return intList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public EntryEntity tickEntry(TickDto tick, EntryEntity entry) {
        if (tick.wrongThrow()) {
            entry.setWrongThrow(entry.getWrongThrow() + 1);
            return entryRepository.save(entry);
        } else {
            Colors color = tick.color();
            String colorEntry = getColorEntry(color, entry);
            String newColorEntry = addTick(colorEntry, tick.number());
            return persistTick(entry, newColorEntry, color);
        }
    }
}
