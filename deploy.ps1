$imageName="tnwl-tool"

./gradlew jib --image=$imageName

if (-not $?) {
    Write-Warning("build image failed")
    return
}