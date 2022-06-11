# Nori Analyzer API

## Test locally
* run server
```
$ ./mvnw package
$ java -jar `ls target/*.jar`

```
* request
```bash
curl -XPOST http://localhost:8080/analyze -d 'text=가리봉역 鄕歌' -s | jq
[
  {
    "token": "가리봉역",
    "type": "COMPOUND",
    "morphemes": [
      {
        "tag": "General Noun",
        "surfaceForm": "가리봉"
      },
      {
        "tag": "General Noun",
        "surfaceForm": "역"
      }
    ]
  },
  {
    "token": "향가",
    "type": "MORPHEME",
    "morphemes": null
  }
]
```