/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.bardzinski.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author kbardzinski
 */
public class PascalTriangle {

    public List<List<Long>> generate(Long maxLevel) {
        var levels = new LinkedList<List<Long>>();
        
        return generateTriangle(levels, maxLevel);
    }

    private List<List<Long>> generateTriangle(LinkedList<List<Long>> levels, Long maxLevel) {
        
        levels.addLast(List.of(1L));
        var levelIdx = 1L;
        
        while(levelIdx<maxLevel) {
            addLevel(levels);
            levelIdx++;
        }
               
        return Collections.unmodifiableList(levels);
    }

    private void addLevel(LinkedList<List<Long>> levels) {
        var prevLevel = levels.getLast();
        var currentLevel = new ArrayList<Long>(prevLevel.size()+1);
        
        currentLevel.add(1L);

        var firstItems = prevLevel.iterator();
        var secondItems = prevLevel.stream().skip(1);
        //manual zip(firstItems,secondItems and put into collection
        secondItems.forEach(item2 -> currentLevel.add(item2 + firstItems.next()));

        currentLevel.add(1L);
        levels.add(Collections.unmodifiableList(currentLevel));
    }
}
