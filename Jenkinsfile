pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "yourdockerhubusername/event-website"
        DOCKER_CREDENTIALS_ID = "dockerhub-creds" // Must be configured in Jenkins
        KUBE_CONFIG_ID = "kubeconfig-creds"       // Must be configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: KUBE_CONFIG_ID]) {
                    sh '''
                    # Update deployment to use the new image build
                    sed -i "s|yourdockerhubusername/event-website:latest|${DOCKER_IMAGE}:${BUILD_ID}|g" k8s/deployment.yaml
                    
                    # Apply configurations
                    kubectl apply -f k8s/deployment.yaml
                    kubectl apply -f k8s/service.yaml
                    '''
                }
            }
        }
    }
}