/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;

import finnstr.libgdx.liquidfun.ParticleSystem;

/** Callback class for ray casts.
 * @see World#rayCast(RayCastCallback, Vector2, Vector2)
 * @author mzechner */

public interface RayCastCallback {

	/** Called for each fixture found in the query. You control how the ray cast proceeds by returning a float: return -1: ignore
	 * this fixture and continue return 0: terminate the ray cast return fraction: clip the ray to this point return 1: don't clip
	 * the ray and continue.
	 * 
	 * The {@link Vector2} instances passed to the callback will be reused for future calls so make a copy of them!
	 * 
	 * @param fixture the fixture hit by the ray
	 * @param point the point of initial intersection
	 * @param normal the normal vector at the point of intersection
	 * @return -1 to filter, 0 to terminate, fraction to clip the ray for closest hit, 1 to continue **/
	public float reportRayFixture (Fixture fixture, Vector2 point, Vector2 normal, float fraction);
	
	/** Called for each particle found in the query. You control how the ray
	* cast proceeds by returning a float:
	* return <=0: ignore the remaining particles in this particle system
	* return fraction: ignore particles that are 'fraction' percent farther
	*   along the line from 'point1' to 'point2'. Note that 'point1' and
	*   'point2' are parameters to b2World::RayCast.
	* @param system the particle system containing the particle
	* @param index the index of the particle in particleSystem
	* @param point the point of intersection bt the ray and the particle
	* @param normal the normal vector at the point of intersection
	* @param fraction percent (0.0~1.0) from 'point0' to 'point1' along the
	*   ray. Note that 'point1' and 'point2' are parameters to
	*   b2World::RayCast.
	* @return <=0 to ignore rest of particle system, fraction to ignore
	* particles that are farther away. */
	public float reportRayParticle(ParticleSystem system, int index, Vector2 point, Vector2 normal, float fraction);

	/** Cull an entire particle system from b2World::RayCast. Ignored in
	* b2ParticleSystem::RayCast.
	* @return true if you want to include particleSystem in the RayCast, or
	* false to cull particleSystem from the RayCast. */
	public boolean shouldQueryParticleSystem(ParticleSystem system);
}
