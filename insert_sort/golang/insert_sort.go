package golang

func InsertSort(arr []int) {
	if arr == nil || len(arr) < 2 {
		return
	}

	for i := 1; i < len(arr); i++ {
		for j := i - 1; j >= 0 && arr[j] > arr[j+1]; j-- {
			SwapWithoutTempArr(arr, j, j+1)
		}
	}
}

func SwapWithoutTempArr(arr []int, a, b int) {
	arr[a] = arr[a] ^ arr[b]
	arr[b] = arr[a] ^ arr[b]
	arr[a] = arr[a] ^ arr[b]
}
