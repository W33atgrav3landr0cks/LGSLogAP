@echo off
git init
git add *.*
git commit -m "first commit"
git branch -M main
git push -u -f origin main
pause
