package golang

import "fmt"

func SelectSort(arr []int) {
	if arr == nil || len(arr) < 2 {
		return
	}

	for i := 0; i < len(arr); i++ {
		minIndex := i
		for j := i + 1; j < len(arr); j++ {
			if arr[j] < arr[minIndex] {
				minIndex = j
			}
		}
		if minIndex != i {
			Swap(arr, i, minIndex)
		}
	}

	fmt.Println(arr)
}

func Swap(arr []int, a int, b int) {
	temp := arr[a]
	arr[a] = arr[b]
	arr[b] = temp
}
