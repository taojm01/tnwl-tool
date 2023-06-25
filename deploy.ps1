$imageName="tnwl-tool"

./gradlew jibDockerBuild

if (-not $?) {
    Write-Warning("build image failed")
    return
}
# docker-compose stop $imageName
# docker-compose rm -f $imageName

docker-compose up -d $imageName
if (-not $?) {
    Write-Warning("{0} start failed" -f $imageName)
    return
}
docker image rm $(docker image ls -a -q)
docker-compose logs -f $imageName
