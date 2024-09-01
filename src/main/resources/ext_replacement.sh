#!/bin/bash
usage() {
    echo "Использование: $0 --file <файл> --file <файл> ... --extension <расширение> --replacement <новое расширение>"
    exit 1
}
if [ $# -lt 4 ]; then
    usage
fi
files=()
extension=""
replacement=""
while [[ $# -gt 0 ]]; do
    case $1 in
        --file)
            shift
            files+=("$1")
            ;;
        --extension)
            shift
            extension="$1"
            ;;
        --replacement)
            shift
            replacement="$1"
            ;;
        *)
            usage
            ;;
    esac
    shift
done

if [ -z "$extension" ] || [ -z "$replacement" ]; then
    usage
fi
for file in "${files[@]}"; do
    if [[ $file == *.$extension ]]; then
        new_file="${file%.$extension}.$replacement"
        echo "$new_file"
    else
        echo "Файл $file не имеет расширения $extension"
    fi
done
