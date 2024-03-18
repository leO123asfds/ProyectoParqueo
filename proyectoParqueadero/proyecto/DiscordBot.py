import json, os
import discord
import datetime
import locale
from discord.ext import commands
from discord.ext.commands import has_permissions 

class Crear_Respuesta():
     def __init__(self, title, content):
          self.title = title
          self.content = content

          self.respuesta = discord.Embed(
               title = self.title,
               description = self.content,
               colour = int("F44336", 16)
          )
     @property
     def enviar(self):
          return self.respuesta
     

def main():
    if os.path.exists('config.json'):
        with open('config.json') as f:
            config_data = json.load(f)
    else:
            template = {'prefix': '!', 'token':"", 'palabrasbaneadas': []}
            with open('config.json', 'w') as f:
                json.dump(template, f)

    palabrasbaneadas = config_data["palabrasbaneadas"]
    prefix = config_data["prefix"]
    token = config_data["token"]
    intents = discord.Intents.all()
    bot = commands.Bot(
		command_prefix = prefix, 
		intents = intents, 
		description = "Bot Prueba")
    # Comandos
    @bot.command(name = "saludar", help = "El bot saluda a todos")
    async def saludar(ctx):
        autor = str(ctx.author)  
        autor_F = autor[:-5]  
        await ctx.reply(f'Hola! {autor_F}, ¿En que te puedo ayudar?')

    @bot.command(name = 'sumar', help = "Suma de dos numeros")
    async def sumar(ctx, num1:int, num2:int):
         suma = num1+num2
         respuesta = Crear_Respuesta("El resultado de la suma es:",suma)
         await ctx.reply(embed = respuesta.enviar)

    @bot.command(name = 'dia', help = "Te dice la fecha de hoy")
    async def dia(ctx):
          locale.setlocale(locale.LC_ALL, 'es_ES.utf8')
          fecha_actual = datetime.datetime.now()
          nombre_dia_semana = fecha_actual.strftime("%A")
          numero_dia = fecha_actual.strftime("%d")
          nombre_mes = fecha_actual.strftime("%B")
          anio = fecha_actual.strftime("%Y")
          fecha_formateada = f"{nombre_dia_semana} {numero_dia} de {nombre_mes} del {anio}"
          respuesta = Crear_Respuesta("El dia de hoy es:",fecha_formateada)
          await ctx.reply(embed = respuesta.enviar)

    @has_permissions(administrator=True)
    @bot.command(help= 'Banear palabra del servidor')
    async def banword(ctx, palabra):
         if palabra.lower() in palabrasbaneadas:
              await ctx.send(embed = Crear_Respuesta('Esa palabra ya esta baneada', None).enviar)
         else:
              palabrasbaneadas.append(palabra.lower())
              with open('config.json','r+') as f:
                   datos = json.load(f)
                   datos['palabrasbaneadas'] = palabrasbaneadas 
                   f.seek(0)
                   f.write(json.dumps(datos))
                   f.truncate()
              respuesta = Crear_Respuesta('Palabra baneada correctamente.',None)
              await ctx.send(embed = respuesta.enviar)

    @has_permissions(administrator=True)
    @bot.command(help= 'Quitar ban a palabra del servidor')
    async def unbanword(ctx, palabra):
         if palabra.lower() in palabrasbaneadas:
              palabrasbaneadas.remove(palabra.lower())
              with open('config.json','r+') as f:
                   datos = json.load(f)
                   datos['palabrasbaneadas'] = palabrasbaneadas 
                   f.seek(0)
                   f.write(json.dumps(datos))
                   f.truncate()
              respuesta = Crear_Respuesta('Palabra eliminada correctamente.',None)
              await ctx.send(embed = respuesta.enviar)
         else:
              respuesta = Crear_Respuesta('Error:','Esa palabra no esta baneada del servidor.')
              await ctx.send(embed = respuesta.enviar)
              
    # Eventos

    @bot.event
    async def on_message(message):
         message_content = message.content.lower()
         message_content = message_content.split(' ')
         for palabrabaneada in palabrasbaneadas:
              if palabrabaneada in message_content:
                   respuesta = Crear_Respuesta('Palabra prohibida.', None)
                   await message.reply(embed = respuesta.enviar)
                   await message.delete()
                   break
         await bot.process_commands(message)

    bot.run(token)

if __name__ == '__main__':
     main()