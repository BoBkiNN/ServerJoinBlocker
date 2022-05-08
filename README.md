# ServerJoinBlocker

Plugin that will allow or not allow players connect to BungeeCord

## Features
* Customizable messages
* List of players that can join during block
* Commands for easy enable/disable block
* Colored messages support

## Commands
* `/bon` - enable block (aliases: `/blockjoinenable`, `/benable`)
* `/boff` - disable block (aliases: `/blockjoindisable`, `/bdisable`)
* `/greload` - will reload BungeeCord, but plugin configuration will be reloaded too

## Permissions
* `serverjoinblocker.enable` - will allow to enable block
* `serverjoinblocker.disable` - will allow to disable block

## config.yml
```yaml
enableMsg: '&cJoins blocked!'
enableMsgAlready: Joins already &cblocked
disableMsg: '&aJoins allowed!'
disableMsgAlready: Joins already &aallowed
kickMsg: '&cSorry, you can`t join now'
reloadMsg: '&aConfiguration reloaded!'
allowJoinTo:
- playername
blockEnabled: true
#dont touch blockEnabled option, commands changing it
```
