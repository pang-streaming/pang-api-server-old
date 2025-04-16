pipeline {
	agent any

    environment {
		IMAGE_NAME = 'your-dockerhub-username/your-image-name'
        IMAGE_TAG = 'latest'
        REGISTRY_CREDENTIALS_ID = 'docker-hub-credentials'
        BRANCH = 'main'
    }

    stages {
		stage('Clone') {
			steps {
				git branch: 'main', url: 'https://github.com/pang-streaming/pang-api-server.git'
            }
            post {
				success {
					echo '레포지토리 가져오기 성공'
                }
                failure {
					error '파이프라인 정지'
                }
            }
        }

        stage('Build Gradle') {
			steps {
				echo 'Build Gradle'
                dir('.') {
					sh 'chmod +x gradlew'
                    sh './gradlew clean build -x test'
                }
            }
            post {
				failure {
					error '파이프라인 정지'
                }
            }
        }

		stage('Build Docker') {
			steps {
				echo 'Build Docker'
                script {
					dockerImage = docker.build("${IMAGE_NAME}")
                }
            }
            post {
				failure {
					error '도커 빌드 실패'
                }
            }
        }

        stage('Push Docker') {
			steps {
				echo 'Push Docker'
                script {
					docker.withRegistry('', REGISTRY_CREDENTIALS_ID) {
						dockerImage.push()
                    }
                }
            }
            post {
				failure {
					error 'This pipeline stops here...'
                }
            }
        }

        stage('Docker Run') {
			steps {
				echo 'Pull Docker Image & Docker Image Run'
                sshagent (credentials: ['ssh']) {
					sh "ssh -o StrictHostKeyChecking=no ubuntu@{서버IP} 'docker pull ${imagename}'"
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@{서버IP} 'docker compose -f /home/ubuntu/spring/compose/docker-compose.yml up --build -d'"
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@{서버IP} 'docker image prune -f'"
                }
            }
        }
	}
}
