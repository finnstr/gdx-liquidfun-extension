
package finnstr.libgdx.liquidfun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/** Holds the values for particle creation
 * @author FinnStr */
public class ParticleDef {
	
	public enum ParticleType {
		/** Water particle. */
		b2_waterParticle (0),
		/** Removed after next simulation step. */
		b2_zombieParticle (1 << 1),
		/** Zero velocity. */
		b2_wallParticle (1 << 2),
		/** With restitution from stretching. */
		b2_springParticle (1 << 3),
		/** With restitution from deformation. */
		b2_elasticParticle (1 << 4),
		/** With viscosity. */
		b2_viscousParticle (1 << 5),
		/** Without isotropic pressure. */
		b2_powderParticle (1 << 6),
		/** With surface tension. */
		b2_tensileParticle (1 << 7),
		/** Mix color between contacting particles. */
		b2_colorMixingParticle (1 << 8),
		/** Call b2DestructionListener on destruction. */
		b2_destructionListenerParticle (1 << 9),
		/** Prevents other particles from leaking. */
		b2_barrierParticle (1 << 10),
		/** Less compressibility. */
		b2_staticPressureParticle (1 << 11),
		/** Makes pairs or triads with other particles. */
		b2_reactiveParticle (1 << 12),
		/** With high repulsive force. */
		b2_repulsiveParticle (1 << 13),
		/** Call b2ContactListener when this particle is about to interact with
		* a rigid body or stops interacting with a rigid body.
		* This results in an expensive operation compared to using
		* b2_fixtureContactFilterParticle to detect collisions between
		* particles. */
		b2_fixtureContactListenerParticle (1 << 14),
		/** Call b2ContactListener when this particle is about to interact with
		* another particle or stops interacting with another particle.
		* This results in an expensive operation compared to using
		* b2_particleContactFilterParticle to detect collisions between
		* particles. */
		b2_particleContactListenerParticle (1 << 15),
		/** Call b2ContactFilter when this particle interacts with rigid bodies. */
		b2_fixtureContactFilterParticle (1 << 16),
		/** Call b2ContactFilter when this particle interacts with other
		* particles. */
		b2_particleContactFilterParticle (1 << 17);

		private int value;

		private ParticleType (int value) {
			this.value = value;
		}

		public int getValue () {
			return value;
		}
	}

	/** Specifies the type of particle. A particle may be more than one type. Multiple types are chained by logical sums, for
	 * example: pd.flags = b2_elasticParticle | b2_viscousParticle **/
	public Array<ParticleType> flags = new Array<ParticleType>();

	/** The world position of the particle. **/
	public final Vector2 position = new Vector2();

	/** The linear velocity of the particle in world co-ordinates. **/
	public final Vector2 velocity = new Vector2();

	/** The color of the particle. **/
	public final Color color = new Color(0, 0, 0, 0);
	
	/** Lifetime of the particle in seconds.  A value <= 0.0f indicates a
	* particle with infinite lifetime. */
	public float lifetime = 0.0f;

	/** An existing particle group to which the particle will be added. */
	public ParticleGroup group;
}
