package golang

func BubbleSort(arr []int) {
	if arr == nil || len(arr) < 2 {
		return
	}

	for i := 0; i < len(arr); i++ {
		for j := 1; j < len(arr)-i; j++ {
			if arr[j-1] > arr[j] {
				Swap(arr, j-1, j)
			}
		}
	}
}

func Swap(arr []int, a int, b int) {
	temp := arr[a]
	arr[a] = arr[b]
	arr[b] = temp
}
