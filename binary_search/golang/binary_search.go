package golang

/**
 * sortArr [min ~ max]
 */
func BinarySearch(sortArr []int, search int) int {
	minIndex := 0
	maxIndex := len(sortArr) - 1
	for minIndex <= maxIndex {
		middle := (maxIndex + minIndex) / 2

		if sortArr[middle] > search {
			maxIndex = middle - 1
		} else if sortArr[middle] < search {
			minIndex = middle + 1
		} else {
			return middle
		}
	}

	return -1
}
