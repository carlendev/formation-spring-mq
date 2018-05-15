pipeline {
  agent {
    docker {
      image 'maven:3.5.3-jdk-10'
      args '-v $HOME/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build Server') {
      steps {
        sh '''
cd server && mvn clean install'''
      }
    }
    stage('Test Server') {
      steps {
        sh 'cd server && mvn test'
      }
    }
    stage('Package Server') {
      steps {
        sh 'cd server && mvn package'
      }
    }
  }
}