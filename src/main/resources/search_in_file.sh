#!/bin/bash

usage() {
    echo "Использование: $0 --file <путь к файлу> --search <строка для поиска>"
    exit 1
}
while [[ "$#" -gt 0 ]]; do
    case $1 in
        --file) FILE_PATH="$2"; shift ;;
        --search) SEARCH_STRING="$2"; shift ;;
        *) usage ;;
    esac
    shift
done

if [ -z "$FILE_PATH" ] || [ -z "$SEARCH_STRING" ]; then
    usage
fi

if [ ! -f "$FILE_PATH" ]; then
    echo "Файл не найден: $FILE_PATH"
    exit 1
fi

MATCH_COUNT=$(grep -o "$SEARCH_STRING" "$FILE_PATH" | wc -l)

if [ "$MATCH_COUNT" -eq 0 ]; then
    echo "Не найдено ни одного совпадения в файле $FILE_PATH"
else
    echo "Найдено $MATCH_COUNT совпадений в файле $FILE_PATH"
fi
