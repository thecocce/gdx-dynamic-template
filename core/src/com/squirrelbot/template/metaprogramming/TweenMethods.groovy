package com.squirrelbot.template.metaprogramming

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.math.Vector2
import com.squirrelbot.template.components.PositionComponent
import com.squirrelbot.template.state.State
import com.squirrelbot.template.tween.MusicAccesor
import com.squirrelbot.template.tween.PositionComponentAccesor
import com.squirrelbot.template.tween.Vector2Accesor
import dorkbox.tweenengine.BaseTween
import dorkbox.tweenengine.Tween
import dorkbox.tweenengine.TweenCallback

/**
 * Created by Ethan on 4/13/2016.
 */
class TweenMethods {

	public static void addToTween()
	{
		Tween.metaClass.switchState = { State state ->
			return addCallback(new TweenCallback() {
			@Override
			void onEvent(int type, BaseTween<?> source) {
				if(type == TweenCallback.Events.COMPLETE)
				{
					Gdx.states.switchState(state)
				}
			}
		})
		}

		Tween.registerAccessor(Music.class, new MusicAccesor());
		Tween.registerAccessor(Vector2.class, new Vector2Accesor());
		Tween.registerAccessor(PositionComponent.class, new PositionComponentAccesor());
	}

}
