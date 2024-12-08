package main

import (
	_ "embed"
	"fmt"
	"sort"
	"strconv"
	"strings"
)

//go:embed input_1.txt
var inputFile string

func main() {
	inputs := strings.Split(inputFile, "\n")
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
	sort.Slice(leftList, func(i, j int) bool {
		return leftList[i] < leftList[j]
	})
	sort.Slice(rightList, func(i, j int) bool {
		return rightList[i] < rightList[j]
	})

	dist := 0
	for i := range leftList {
		if leftList[i] > rightList[i] {
			dist += (leftList[i] - rightList[i])
			continue
		}
		dist += (rightList[i] - leftList[i])
	}
	fmt.Println(dist)
}
