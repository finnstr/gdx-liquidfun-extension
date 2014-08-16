
package finnstr.libgdx.liquidfun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;

import finnstr.libgdx.liquidfun.ParticleDef.ParticleType;

/** Holds the values for a ParticleGroup creation
 * @author FinnStr */
public class ParticleGroupDef {

	/** SolidParticleGroup: resists penetration, RigidParticleGroup: keeps its shape */
	public enum ParticleGroupType {
		/// Prevents overlapping or leaking.
		b2_solidParticleGroup (1 << 0),
		/// Keeps its shape.
		b2_rigidParticleGroup (1 << 1),
		/// Won't be destroyed if it gets empty.
		b2_particleGroupCanBeEmpty (1 << 2),
		/// Will be destroyed on next simulation step.
		b2_particleGroupWillBeDestroyed (1 << 3),
		/// Updates depth data on next simulation step.
		b2_particleGroupNeedsUpdateDepth (1 << 4);

		private int value;

		private ParticleGroupType (int value) {
			this.value = value;
		}

		public int getValue () {
			return value;
		}
	}

	/** The particle-behavior flags. **/
	public Array<ParticleType> flags = new Array<ParticleType>();

	/** The group-construction flags. **/
	public Array<ParticleGroupType> groupFlags = new Array<ParticleGroupType>();

	/** The world position of the group. Moves the group's shape a distance equal to the value of position. **/
	public final Vector2 position = new Vector2();

	/** The world angle of the group in radians. Rotates the shape by an angle equal to the value of angle. **/
	public float angle = 0;

	/** The linear velocity of the group's origin in world co-ordinates. **/
	public final Vector2 linearVelocity = new Vector2();

	/** The angular velocity of the group. **/
	public float angularVelocity = 0;

	/** The color of all particles in the group. **/
	public final Color color = new Color(0, 0, 0, 0);

	/** The strength of cohesion among the particles in a group with flag b2_elasticParticle or b2_springParticle. **/
	public float strength = 1;

	/** Shape containing the particle group. **/
	public Shape shape = null;

	/** The interval of particles in the shape.
	* If it is 0, b2_particleStride * particleDiameter is used instead. */
	public float stride = 0;

	/** The number of particles in addition to ones added in the shape. */
	public int particleCount = 0;

	/** The initial positions of the particleCount particles. */
	public Vector2 positionData = null;

	/** Lifetime of the particle group in seconds.  A value <= 0.0f indicates a
	* particle group with infinite lifetime. */
	public float lifetime = 0.0f;

	/** An existing particle group to which the particles will be added. */
	public ParticleGroup group = null;

}
