#!/bin/bash
if [ $# -ne 1 ]; then
    echo "Использование: $0 <число>"
    exit 1
fi
number=$1

if ! [[ $number =~ ^[0-9]+$ ]]; then
    echo "Аргумент должен быть положительным целым числом"
    exit 1
fi

half=$(($number / 2))
product=1
for ((i = 1; i <= half; i++)); do
    product=$((product * i))
done
sum=0
for ((i = half + 1; i <= number; i++)); do
    sum=$((sum + i))
done
echo "mult : $product"
echo "sum: $sum"
