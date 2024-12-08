package main

import (
	_ "embed"
	"fmt"
	"strconv"
	"strings"
)

//go:embed input_1.txt
var InputFile string

func day1part2() {
	inputs := strings.Split(InputFile, "\n")
	var leftList []int
	var rightList []int
	for _, input := range inputs {
		locationIDs := strings.Split(strings.TrimSpace(input), "   ")
		if locationIDs[0] == "" {
			continue
		}
		l, _ := strconv.Atoi(locationIDs[0])
		r, _ := strconv.Atoi(locationIDs[1])
		leftList = append(leftList, l)
		rightList = append(rightList, r)
	}
	countMap := make(map[int]int)
	for _, v := range rightList {
		_, ok := countMap[v]
		if !ok {
			countMap[v] = 1
			continue
		}
		countMap[v]++
	}
	similarity := 0
	for _, v := range leftList {
		val, ok := countMap[v]
		if !ok {
			continue
		}
		similarity += v * val
	}
	fmt.Println(similarity)
}
