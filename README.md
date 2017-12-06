The simplest app possible. Serves up a single page, and a single API.


Clone
-----
git clone etc.


Run locally
--------
Run as Spring boot app (8180)

Check we can see http://localhost:8180/account


Expose to internet
--------------
Expose to internet via ngrok

~/Devtools/ngrok http -subdomain=helloworldapp 8180


Declare app, connect to server
-------
Create the app

Copy secret key into the code

Update back end server

In TAS controller, uncomment the tazzy-secret checking

ref the doc for installing an app API flow

install and uninstall the app, watch the install logging messages in STS



Add the network proxy
-------------
very useful for debugging

(ref network diagram)

start charles, add a reverse proxy inside charles
	8181 -> 127.0.0.1:8180

update the ngrok tunnel to now point to 8181 

edit application.properties to 8888

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


