#!/bin/sh
cd "d/TIBCO Projects/May 2016-17/JetBlue/DEVOPS/ArtifactBuilder/jenkins_workspace/FSNT/releases/1.0.0"
git checkout JB
git add .
git commit -am "Release1"
git push