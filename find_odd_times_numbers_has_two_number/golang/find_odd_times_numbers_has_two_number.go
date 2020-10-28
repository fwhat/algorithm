package golang

func FindOddTimesNumbersHasTwoNumber2(arr []int) (int, int) {
	eor1 := 0
	for _, v := range arr {
		eor1 ^= v
	}

	rightOne := FindTheLeftmost1InANumber(eor1)

	a := 0
	for _, v := range arr {
		if (rightOne & v) != 0 {
			a ^= v
		}
	}

	return a, eor1 ^ a
}

func FindTheLeftmost1InANumber(a int) int {
	return a & (^a + 1)
}
