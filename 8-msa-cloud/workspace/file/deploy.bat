@rem ===== 1. 다른 프로젝트에서 할 때는 사전에 mkdir /home/ubuntu/app/프로젝트명 디렉터리를 만들어야함
@rem ===== 2. 키파일명 myworkspace.pem 제외하고 "myworkspace" 이것을 프로젝트명으로 바꿈

@rem ===== 1. 빌드된 jar파일을 서버에 전송
scp -i "c:\keyfile\myworkspace.pem" -r ./build/libs/file*.jar ubuntu@15.164.54.22:/home/ubuntu/app/file
@rem ===== 2. jar파일을 실행하는 run.sh 스크립트 파일을 서버에 전송
scp -i "c:\keyfile\myworkspace.pem" -r ./run.sh ubuntu@15.164.54.22:/home/ubuntu/app/file
@rem ===== 3. run.sh 스크립파일을 실행가능하도록 권한부여(777 -> rwx rwx rwx)
ssh -i "c:\keyfile\myworkspace.pem" ubuntu@15.164.54.22 "sudo chmod 777 /home/ubuntu/app/file/run.sh"
@rem ===== 4. jar파일 있는 디렉터리까지 이동하고, run.sh로 기존 프로세스 죽이고 실행
ssh -i "c:\keyfile\myworkspace.pem" ubuntu@15.164.54.22 "cd /home/ubuntu/app/file; ./run.sh file"


