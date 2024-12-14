package main

import (
	_ "embed"
	"fmt"
	"strconv"
	"strings"
)

//go:embed input.txt
var InputFile string

func main() {
	reports := strings.Split(InputFile, "\n")

	safeReportsCount := 0
	for _, report := range reports {
		levels := strings.Fields(report)
		if len(levels) == 0 {
			continue
		}

		var isAsc bool
		l1, _ := strconv.Atoi(levels[1])
		l0, _ := strconv.Atoi(levels[0])
		if l1 > l0 {
			isAsc = true
		} else if l1 == l0 {
			continue
		} else {
			isAsc = false
		}

		isSafe := true
		for i := 1; i < len(levels); i++ {
			v1, _ := strconv.Atoi(levels[i])
			v2, _ := strconv.Atoi(levels[i-1])
			d := v1 - v2
			if isAsc && (d > 3 || d <= 0) {
				isSafe = false
				break
			} else if !isAsc && (d < -3 || d >= 0) {
				isSafe = false
				break
			}
		}

		if isSafe {
			safeReportsCount++
		}
	}
	fmt.Println(safeReportsCount)
}
