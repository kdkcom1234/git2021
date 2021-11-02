@rem ===== 1. 다른 프로젝트에서 할 때는 사전에 mkdir /home/ubuntu/app/프로젝트명 디렉터리를 만들어야함
@rem ===== 2. 키파일명을 맞추고 "myworkspace" 이것을 프로젝트명으로 바꿈

@rem ===== 1. 빌드된 jar파일을 서버에 전송
scp -i "c:\keyfile\myworkspace.pem" -r ./build/libs/myworkspace*.jar ubuntu@15.164.54.22:/home/ubuntu/app/myworkspace
@rem ===== 2. 기존 프로세스 종료
ssh -i "c:\keyfile\myworkspace.pem" ubuntu@15.164.54.22 "pkill -9 -f java"
@rem ===== 3. dev프로필로 jar 파일 실행
ssh -i "c:\keyfile\myworkspace.pem" ubuntu@15.164.54.22 "cd /home/ubuntu/app/myworkspace; nohup java -Dspring.profiles.active=dev -jar myworkspace*.jar 1>myworkspace.log 2>&1 &"


