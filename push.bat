@echo off
git init
git add *.*
git commit -m "first commit"
git branch -M main
git add remote origin https://github.com/W33atgrav3landr0cks/LGSLogAP.git
git push -u -f origin main
pause
