# Minimal reproduction of gomobile network issue

## Start example

1. Bind the android lib

```sh
cd gotcp
go mod download
gomobile bind -target=android -o gotcp.aar
```

1. Start android app
