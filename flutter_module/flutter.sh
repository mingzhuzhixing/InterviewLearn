#!/bin/bash
# This is flutter build

# 初始化记录项目pwd
echo -------初始化文件夹目录完成
projectDir=`pwd`
# 获取 flutter sdk
rootFlutter=`which flutter`
# 提取 flutter skd路径
rootDir=${rootFlutter%/*}

#echo "项目pwd "$projectDir
#echo "flutter sdk "$rootFlutter
#echo "flutter skd路径"$rootDir

###### 1. 清理项目
echo -------清理项目 开始 projectDir
${rootFlutter} clean
echo -------清理项目 完成

###### 2. 同步更新
echo -------同步更新 开始
${rootFlutter} packages get
echo -------同步更新 完成

#创建目录
dir="build/host/outputs/repo"
mkdir -m 777 -p $dir
echo -------build目录创建成功 $dir

####### 3. 替换文件版本号
line=$(sed -n '/version /=' .android/Flutter/build.gradle)
#echo $line

#读取文件
v=`grep sonatypeVersion bak/gradle.properties|cut -d'=' -f2`
echo -------扫描 app/build.gradle 中的 version $v

#VERSION='version '\'$v\'
##echo $VERSION
#
##插入行
#newline=$(expr $line - 1)
##删除行
#sed  -i "" "$line d"  .android/Flutter/build.gradle
##替换版本号（不知道为什么写一行不行）
#sed -i "" "$newline a\\
#$VERSION
#" .android/Flutter/build.gradle
#
#echo "flutter_module.aar 版本 "$v
#
#if [ $? -eq 0 ]; then
#  echo "成功"
#else
#  echo "失败"
#    exit 1
#fi

####### 3 修改aar_init_script.gradle

#获取修改文件路径 3.1
aar_gradle_path=${rootDir/bin/'packages/flutter_tools/gradle/aar_init_script.gradle'}
echo -------获取系统脚本目录 $aar_gradle_path

#copy文件备份 3.2
if [ ! -f "$aar_gradle_path.bak1" ]; then
  cp $aar_gradle_path $aar_gradle_path.bak1
else
  cp $aar_gradle_path.bak1 $aar_gradle_path
fi
echo -------备份恢复系统脚本 完成

#修改文字 3.3
aline=$(sed -n '/repository(url/=' $aar_gradle_path)
#sed -i "" "${aline} r bak/uploadArchives.txt" $aar_gradle_path
sed  -i "" "$aline d"  $aar_gradle_path

echo -------修改系统脚本 完成

####### 4. 编译aar
echo -------编译aar 开始
${rootFlutter} build aar --no-debug --no-profile --build-number $v
echo -------编辑aar 结束

#恢复flutter build文件 4.1
cp $aar_gradle_path.bak1 $aar_gradle_path
echo -------备份恢复系统脚本 完成

