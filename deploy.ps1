$imageName="tnwl-tool"
$nextVersion="0.0.1"
$registryImage="registry.cn-shanghai.aliyuncs.com/taojm/{0}:{1}" -f $imageName, $nextVersion

./gradlew jib --image=$registryImage
if (-not $?) {
    Write-Warning("build image failed")
    return
}

docker pull $registryImage

docker stop $imageName
docker rm -f $imageName
docker run -it -d --name $imageName -p 7007:7007 $registryImage

if (-not $?) {
    Write-Warning("{0} start failed" -f $imageName)
    return
}
docker image rm $(docker image ls -a -q)
docker logs -f -t $imageName