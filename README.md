The simplest app possible. Serves up a single page, and a single API.


Clone
-----
git clone etc.


Run locally
--------
(e.g., import as Maven project in STS, Run as Spring boot app)

Check we can see http://localhost:8180/account


Expose to internet
--------------
Expose to internet via ngrok

~/Devtools/ngrok http 8180

Make note of resulting tunnel name.


Declare app, connect to server
-------
Go to https://developer.talentappstore.com.

Create the app, giving it name and shortcode

Copy the new app's secret key back into this project (application.properties file)

On the app's tazzy tab, update back end server to point to ngrok tunnel started in previous step


Secure the app
-----------
In TASController.java, uncomment the tazzy-secret checking.


Watch incoming APIs
-----------
Ref the doc for installing an app http://devdocs.talentappstore.com/doc/install.html

Uninstall and uninstall the app.

We can see the incoming core API calls in the app's logging!


Add the Charles web debugging proxy
-------------
Very useful for debugging. No need to log API calls in your code. Install Charles proxy from internet.

Our network will be like this, so Charles can inspect incoming and outgoing traffic to the app.


````
 |
internet
 |                 8181 +---------+    8180 +-----------------
 |-(ngrok tunnel)------>|-------->|-------->|
 |                      | charles |         |  your server
 |                      | proxy   |         |
 |<---------------------|<--------|<--------|
 |                      +---------+ 8888    +-------------
 |


````

Start charles, add a reverse proxy inside charles
	8181 -> 127.0.0.1:8180

start a new ngrok tunnel pointing to 8181

~/Devtools/ngrok http 8181

update the app's back end server with the new ngrok tunnel   

Now all traffic in to your server is flowing through charles.

 

edit application.properties to 8888

Now all traffic out of your server is flowing through charles.

uninstall, reinstall


review traffic in charles

observe the incoming and outgoing tazzy-secret header

Add GET /appStatus
--------------
Look at another app that has a setup page 

refer https://github.com/talentappstore/tas-core-apis/wiki/09.-Apps:-install-and-appearance

review API flow

Review the endpoint in TenantAPI Controller

No traffic has flowed yet - go and produce the API within developer site

Now we see GET /appStatus calls coming in

Click through to the page - displays fine


SSO
------
Copy link through to Firefox private session - notice we can see it - security problem

Back to Chrome

Update the principal type of our app

In AccountController, add SSO stuff to the endpoint

See how outgoing API calls are made

Change acount.html accordingly

In TenantAPI Controller, refer to the new signature in GET /appStatus

Uinstall, reinstall

Refresh the app page, view the account page now - SSO happened invisibly

We're also seeing outgoing API calls


More SSO
-------
As tenant, add an identity provider for LinkedIn (user) 

Jump to Firefox private window so see the effect

Paste the account link in, see new login experience


