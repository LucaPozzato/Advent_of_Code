#!/bin/bash

while getopts 'd:s:' FLAG
do
    case "${FLAG}" in
        d) DAY=${OPTARG};;
        s) SET_FILE=${OPTARG}; SET=1;;
    esac
done

if [[ "$SET" -eq 1 ]]
then
    export INPUT_FILE="$SET_FILE";
    echo ${INPUT_FILE}
else
    export INPUT_FILE="Day_${DAY}/input.txt";
    echo ${INPUT_FILE}
fi

javac Day_${DAY}/code.java
java Day_${DAY}/code