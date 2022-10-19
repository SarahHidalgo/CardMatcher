# Projet_informatique

Environnement Devops pour le projet informatique de FISE2.

## Avoir le projet sur son git perso

Dans un premier temps, suivre les étapes du TD1 mais sur votre ordi et pas sur la VM ; c'est à dire :
- Télécharger git sur son ordi.
- Entrer la commande : `ssh-keygen`. Cela va vous créer une clé sur votre ordi qui vous servira à faire un lien entre votre ordi et Gitlab.
- Entrer la commande : `$ cat ~/.ssh/id_rsa.pub`. Cela va vous donner votre clé ssh commençant par ssh-rsa. Copier cette clé.
- Aller dans Gitlab - Mon Profil - SSH Keys et coller votre clé.
- Aller sur le projet dans Gitlab puis le cloner grâce à la clé SSH du projet (commande `git clone <clé ssh>`)

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://devops.telecomste.fr/pontiggia.valentin/projet_informatique.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](https://devops.telecomste.fr/pontiggia.valentin/projet_informatique/-/settings/integrations)

## Collaborate with your team

- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Automatically merge when pipeline succeeds](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing(SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Projet informatique

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Contributeurs

Product Owner : Mohammed Alimoussa

Scrum Master : 

Expert Java : Pierre Jean Drevet

**Equipe de développement** : Mélodie Coué, Fatima Ezzahrae Errami, Sarah Hidalgo, Nabil Chemani, Xavier Tardy, Valentin Pontiggia


## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
