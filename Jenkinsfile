pipeline {
	agent any

    environment {
        IMAGE_NAME = "pangstream/pang-api-server"
        REGISTRY_CREDENTIAL = "dockerhub-token"
        GIT_CREDENTIAL = "github-token"
        SSH_CREDENTIAL = "ssh-credential-id"
        SERVER_USER = "ec2-user"
        //SERVER_IP = "your.server.ip"
        CONTAINER_NAME = "pang-api-server"
        APP_PORT = "8080"
        TAG = "latest"
    }

    stages {
		stage('Git Pull') {
			steps {
				echo 'Cloning Repository...'
                git url: 'git@github.com:your/repo.git',
                    branch: 'main',
                    credentialsId: "${GIT_CREDENTIAL}"
            }
        }

        stage('Gradle Build') {
			steps {
				echo 'Building with Gradle...'
                sh 'chmod +x ./gradlew'
                sh './gradlew clean build -x test'
            }
        }

        stage('Docker Build') {
			steps {
				echo 'Building Docker image...'
                script {
					dockerImage = docker.build("${IMAGE_NAME}:${TAG}")
                }
            }
        }

        stage('Docker Push') {
			steps {
				echo 'Pushing Docker image...'
                script {
					docker.withRegistry('', "${REGISTRY_CREDENTIAL}") {
						dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy on Remote Server') {
			steps {
				echo 'Deploying on remote server...'
                sshagent (credentials: ["${SSH_CREDENTIAL}"]) {
					// 도커 이미지 pull
                    sh "ssh -o StrictHostKeyChecking=no ${SERVER_USER}@${SERVER_IP} 'docker pull ${IMAGE_NAME}:${TAG}'"
                    // 기존 컨테이너 중지 및 삭제
                    sh "ssh -o StrictHostKeyChecking=no ${SERVER_USER}@${SERVER_IP} 'docker ps -q --filter name=${CONTAINER_NAME} | grep -q . && docker rm -f \$(docker ps -aq --filter name=${CONTAINER_NAME}) || true'"
                    // 새 컨테이너 실행
                    sh "ssh -o StrictHostKeyChecking=no ${SERVER_USER}@${SERVER_IP} 'docker run -d --name ${CONTAINER_NAME} -p ${APP_PORT}:${APP_PORT} ${IMAGE_NAME}:${TAG}'"
                }
            }
        }
    }
}
