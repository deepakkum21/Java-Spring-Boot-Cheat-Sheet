## check your Git configuration

```sh
git config -l
```

## setup your Git username, email

```sh
git config --global user.name "Deepak"
git config --global user.email "deepak@demomail.com"
```

## cache your login credentials in Git

```sh
git config --global credential.helper cache
```

## add a file to the staging area

```sh
git add filename_here
```

## add all files in the staging area

```sh
git add .
```

## add only certain files to the staging area

```sh
git add filPattern*
```

## check a repository's status in Git

```sh
git status
```

## commit changes

```sh
git commit      // for msg opens a text editor
git commit -m "your commit message here"
```

## commit changes (and skip the staging area)

```sh
git commit -a -m"your commit message here"
```

## see your commit history in Git

```sh
git log
```

## see your commit history including changes in Git

```sh
git log -p
```

## see a specific commit in Git

```sh
git show commit-id
```

## to see log stats in Git

```sh
git log --stat
```

## see changes made before committing them using "diff" in Git

```sh
git diff
git diff all_checks.py
git diff --staged
```

## see changes using "git add -p"

```sh
git add -p
```

## remove tracked files from the current working tree in Git

```sh
git rm fileName
```

## rename files in Git

```sh
git mv oldfile newfile
```

## revert unstaged changes in Git

```sh
git checkout filename
```

## revert staged changes in Git

```sh
git reset HEAD filename
git reset HEAD -p
```

![cheatsheet](https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2Ff8fa0a70-5806-411b-90ea-71ce6a007fe4_1280x1425.gif)

## 🔧 Basic Commands

```sh
git init – Initialize a new Git repository.
git clone <repo_url> – Clone a remote repository.
git status – Check the status of your working directory.
git add <file> – Stage changes for commit.
git commit -m "message" – Commit staged changes with a message.
git push – Push your local commits to the remote repository.
git pull – Fetch and merge changes from the remote repo.
git diff – Show changes in the working directory (uncommitted changes).
git diff --staged – Show changes between the staging area and last commit.
```

## 🛠️ Branching & Merging

```sh
git branch – List branches.
git branch <branch_name> – Create a new branch.
git checkout <branch_name> – Switch to another branch.
git checkout -b <branch_name> – Create and switch to a new branch.
git merge <branch_name> – Merge a branch into the current one.
git branch -d <branch_name> – Delete a branch after merging.
git branch -D <branch_name> – Forcefully delete a branch, even if it hasn’t merged.
```

## 🔄 Synchronization

```sh
git fetch – Download changes from remote without merging.
git rebase <branch> – Reapply commits on top of another branch to maintain linear history.
git pull --rebase – Fetch and reapply your changes on top of the latest remote changes.
git remote add <name> <url> – Add a new remote repository.
```

## 🎯 Advanced Git

```sh
git stash – Temporarily save changes without committing.
git stash pop – Reapply stashed changes.
git cherry-pick <commit> – Apply a specific commit to your current branch.
git log --oneline – View simplified commit history.
git reflog – Show the history of your reference changes (e.g., checkout, resets).
git log --graph --decorate --all – Show a visual commit history.
```

## 🚨 Undoing Changes

```sh
git reset <file> – Unstage a file.
git reset --soft <commit> – Reset to a commit but keep changes in the working directory.
git reset --hard <commit> – Completely reset to a previous commit, discarding changes.
git revert <commit> – Create a new commit that undoes a specific commit.
```

## ⚙️ Collaborating with Others

```sh
git fork – Fork a repository on GitHub (via UI) to start contributing.
git pull origin <branch> – Pull changes from the original remote branch.
git push origin <branch> – Push your branch to the original repository for collaboration.
```
