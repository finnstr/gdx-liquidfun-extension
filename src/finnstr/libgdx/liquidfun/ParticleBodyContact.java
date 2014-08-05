package finnstr.libgdx.liquidfun;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class ParticleBodyContact {
	// @off
	/*JNI
#include <Box2D/Box2D.h>
	 */
	
	/** the address **/
	public long addr;

	/** the world **/
	public World world;
	
	/** the particleSystem */
	public ParticleSystem particleSystem;

	public ParticleBodyContact (World world, long addr) {
		this.addr = addr;
		this.world = world;
	}
	
	/** Index of the particle making contact. */
	public int getIndex() {
		return jniGetIndex(addr);
	}
	
	private native int jniGetIndex(long addr); /*
		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->index;
	*/
	
	/** The body making contact. */
	public Body getBody() {
		return world.bodies.get(jniGetBodyAddr(addr));
	}
	
	private native long jniGetBodyAddr(long addr); /*
		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return (jlong) contact->body;
	*/
	
	/** The specific fixture making contact. */
	public Fixture getFixture() {
		return world.fixtures.get(jniGetFixtureAddr(addr));
	}
	
	private native long jniGetFixtureAddr(long addr); /*
		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return (jlong) contact->fixture;
	*/
	
	/** Weight of the contact. A value between 0.0f and 1.0f. */
	public float getWeight() {
		return jniGetIndex(addr);
	}
	
	private native float jniGetWeight(long addr); /*
		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->weight;
	*/
	
	private Vector2 normal = new Vector2();
	
	/** The normalized direction from the particle to the body. */
	public Vector2 getNormal() {
		normal.set(jniGetNormalX(addr), jniGetNormalY(addr));
		return normal;
	}
	
	private native float jniGetNormalX(long addr); /*
		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->normal.x;
	 */
	
	private native float jniGetNormalY(long addr); /*
		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->normal.y;
	 */
	
	/** The effective mass used in calculating force. */
	public float getMass() {
		return jniGetIndex(addr);
	}
	
	private native float jniGetMass(long addr); /*
		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->mass;
	*/
}
