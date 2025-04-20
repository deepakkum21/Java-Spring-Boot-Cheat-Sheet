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
