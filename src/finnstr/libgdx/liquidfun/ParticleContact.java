package finnstr.libgdx.liquidfun;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ParticleContact {
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
	
	public ParticleContact (World world, long addr) {
		this.addr = addr;
		this.world = world;
	}
	
	/** Index of the respective particle making contact. */
	public int getIndexA() {
		return jniGetIndexA(addr);
	}
	
	private native int jniGetIndexA(long addr); /*
		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetIndexA();
	*/
	
	/** Index of the respective particle making contact. */
	public int getIndexB() {
		return jniGetIndexB(addr);
	}
	
	private native int jniGetIndexB(long addr); /*
		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetIndexB();
	*/
	
	/** Weight of the contact. A value between 0.0f and 1.0f.
	* 0.0f ==> particles are just barely touching
	* 1.0f ==> particles are perfectly on top of each other */
	public float getWeight() {
		return jniGetWeight(addr);
	}
	
	private native float jniGetWeight(long addr); /*
		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetWeight();
	*/
	
	private Vector2 normal = new Vector2();
	
	/** The normalized direction from A to B. */
	public Vector2 getNormal() {
		normal.set(jniGetNormalX(addr), jniGetNormalY(addr));
		return normal;
	}
	
	private native float jniGetNormalX(long addr); /*
		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetNormal().x;
	 */
	
	private native float jniGetNormalY(long addr); /*
		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetNormal().y;
	 */
}
