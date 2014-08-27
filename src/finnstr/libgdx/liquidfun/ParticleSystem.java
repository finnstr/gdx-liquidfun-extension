
package finnstr.libgdx.liquidfun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.Pool;

/** Manages all particles; read http://google.github.io/liquidfun/ for more information
 * @author FinnStr */
public class ParticleSystem {
	// @off
	/*JNI
#include <Box2D/Box2D.h>
	*/
	
	protected final Pool<ParticleGroup> freeParticleGroups = new Pool<ParticleGroup>(100, 200) {
		@Override
		protected ParticleGroup newObject() {
			return new ParticleGroup(0);
		}
	};
	
	private final long addr;
	private final World world;
	
	/** all known particleGroups **/
	protected final LongMap<ParticleGroup> particleGroups = new LongMap<ParticleGroup>(100);
	
	public ParticleSystem(World world, ParticleSystemDef def) {	
		long worldAddr = world.getAddress();
		this.world = world;
		
		addr = jniCreateParticleSystem(worldAddr, def.radius, def.pressureStrength, 
			def.dampingStrength, def.elasticStrength, def.springStrength, def.viscousStrength,
			def.surfaceTensionPressureStrength, def.surfaceTensionNormalStrength, def.repulsiveStrength,
			def.powderStrength, def.ejectionStrength, def.staticPressureStrength, 
			def.staticPressureRelaxation, def.staticPressureIterations, def.colorMixingStrength, def.destroyByAge,
			def.lifetimeGranularity, def.strictContactCheck, def.density, def.gravityScale, def.maxCount);
		
		this.world.particleSystems.put(addr, this);
	}
	
	private native long jniCreateParticleSystem(long worldAddr, float radius, float pressureStrength, 
		float dampingStrength, float elasticStrength, float springStrength, float viscousStrength,
		float surfaceTensionPressureStrength, float surfaceTensionNormalStrength, float repulsiveStrength,
		float powderStrength, float ejectionStrength, float staticPressureStrength, 
		float staticPressureRelaxation, int staticPressureIterations, float colorMixingStrength, boolean destroyByAge,
		float lifetimeGranularity, boolean strictContactCheck, float density, float gravityScale, int maxCount); /*
		b2ParticleSystemDef def;
		def.radius = radius;
		def.pressureStrength = pressureStrength;
		def.dampingStrength = dampingStrength;
		def.elasticStrength = elasticStrength;
		def.springStrength = springStrength;
		def.viscousStrength = viscousStrength;
		def.surfaceTensionPressureStrength = surfaceTensionPressureStrength;
		def.surfaceTensionNormalStrength = surfaceTensionNormalStrength;
		def.repulsiveStrength = repulsiveStrength;
		def.powderStrength = powderStrength;
		def.ejectionStrength = ejectionStrength;
		def.staticPressureStrength = staticPressureStrength;
		def.staticPressureRelaxation = staticPressureRelaxation;
		def.staticPressureIterations = staticPressureIterations;
		def.colorMixingStrength = colorMixingStrength;
		def.destroyByAge = destroyByAge;
		def.lifetimeGranularity = lifetimeGranularity;
		def.strictContactCheck = strictContactCheck;
		def.density = density;
		def.gravityScale = gravityScale;
		def.maxCount = maxCount;
		
		b2World* world = (b2World*)worldAddr;
		return (jlong)world->CreateParticleSystem(&def);
	*/
	
	public void destroyParticleSystem() {
		jniDestroyParticleSystem(world.getAddress(), addr);
		world.particleSystems.remove(addr);
	}
	
	private native void jniDestroyParticleSystem(long worldAddr, long systemAddr); /*
		b2World* world = (b2World*)worldAddr;
		b2ParticleSystem* system = (b2ParticleSystem*)systemAddr;
		
		world->DestroyParticleSystem(system);
	*/
	
	/** @return Returns the index of the particle. */
	public int createParticle(ParticleDef def) {
		int flags;
		if(def.flags.size == 0) flags = 0;
		else {
			flags = def.flags.get(0).getValue();
			for(int i = 1; i < def.flags.size; i++) {
				flags = ((int)(flags | def.flags.get(i).getValue()));
			}
		}
		
		boolean addToGroup = true;
		if(def.group == null) addToGroup = false;
		
		return jniCreateParticle(addr, flags, def.position.x, def.position.y, def.velocity.x, def.velocity.y,
			(int) (def.color.r * 255f), (int) (def.color.g * 255f), (int) (def.color.b * 255f), (int) (def.color.a * 255f),
			def.lifetime, addToGroup, addToGroup ? def.group.addr : -1);
	}
	
	private native int jniCreateParticle(long addr, int flags, float positionX, float positionY, float velocityX, float velocityY, 
			int colorR, int colorG, int colorB, int colorA, float lifetime, boolean addToGroup, long groupAddr); /*
		b2ParticleDef particleDef;
		particleDef.flags = flags;
		particleDef.position.Set( positionX, positionY );
		particleDef.velocity.Set( velocityX, velocityY );
		particleDef.color.Set(colorR, colorG, colorB, colorA);
		particleDef.lifetime = lifetime;
		if(addToGroup) particleDef.group = (b2ParticleGroup*)groupAddr;
		
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 index = system->CreateParticle( particleDef );
		return (jint)index;
	*/

	/** Removes a particle
	 * @param index The index of the particle given by createParticle() */
	public void destroyParticle(int index) {
		jniDestroyParticle(addr, index);
	}
	
	private native void jniDestroyParticle(long addr, int index); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->DestroyParticle( index, false );
	*/
	
	/** Destroy the Nth oldest particle in the system.
	* The particle is removed after the next b2World::Step().
	* @param index of the Nth oldest particle to destroy, 0 will destroy the
	* oldest particle in the system, 1 will destroy the next oldest
	* particle etc. */
	public void destroyOldestParticle(int index) {
		jniDestroyOldestParticle(addr, index);
	}
	
	private native void jniDestroyOldestParticle(long addr, int index); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->DestroyOldestParticle(index, false);
	*/
	
	/** Removes all particles in the bounds of the shape
	 * @param shape
	 * @param transform transformation of the shape
	 * @return the number of particles destroyed*/
	public int destroyParticleInShape(Shape shape, Transform transform) {
		return jniDestroyParticleInShape(addr, shape.getAddress(), transform.getPosition().x, transform.getPosition().y, transform.getRotation());
	}
	
	private native int jniDestroyParticleInShape(long addr, long shapeAddr, float transformPosX, float transformPosY, float angle); /*
		b2Shape* shape = (b2Shape*)shapeAddr;
		b2Transform transform;
		transform.Set( b2Vec2( transformPosX, transformPosY ), angle );
		
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->DestroyParticlesInShape( *shape, transform, false );
	*/

	public ParticleGroup createParticleGroup(ParticleGroupDef groupDef) {
		int flags;
		if(groupDef.flags.size == 0) flags = 0;
		else {
			flags = groupDef.flags.get(0).getValue();
			for(int i = 1; i < groupDef.flags.size; i++) {
				flags = ((int)(flags | groupDef.flags.get(i).getValue()));
			}
		}
		
		int groupFlags;
		if(groupDef.groupFlags.size == 0) groupFlags = 0;
		else {
			groupFlags = groupDef.groupFlags.get(0).getValue();
			for(int i = 1; i < groupDef.groupFlags.size; i++) {
				flags = ((int)(flags | groupDef.groupFlags.get(i).getValue()));
			}
		}
		
		float positionDataX = 0;
		float positionDataY = 0;
		boolean positionDataSet = groupDef.positionData != null;
		if(positionDataSet) {
			positionDataX = groupDef.positionData.x;
			positionDataY = groupDef.positionData.y;
		}
		
		long addrParticleGroup = jniCreateParticleGroup(addr, flags, groupFlags, groupDef.position.x, groupDef.position.y, groupDef.angle,
				groupDef.linearVelocity.x, groupDef.linearVelocity.y, groupDef.angularVelocity, 
				(int) (groupDef.color.r * 255f), (int) (groupDef.color.g * 255f), 
				(int) (groupDef.color.b * 255f), (int) (groupDef.color.a * 255f),
				groupDef.strength, groupDef.shape.getAddress(), groupDef.stride, groupDef.particleCount, positionDataSet,
				positionDataX, positionDataY, groupDef.lifetime, groupDef.group == null ? -1 : groupDef.group.addr);
		
		ParticleGroup group = freeParticleGroups.obtain();
		group.addr = addrParticleGroup;
		this.particleGroups.put(addrParticleGroup, group);
		return group;
	}
	
	private native long jniCreateParticleGroup(long addr, int flags, int groupFlags, float positionX, 
		float positionY, float angle, float linVelocityX, float linVelocityY, 
		float angularVelocity, int colorR, int colorG, int colorB, int colorA, 
		float strength, long shapeAddr, float stride, int particleCount, boolean positionDataSet, 
		float positionDataX, float positionDataY, float lifetime, long groupAddr); /*
		b2ParticleGroupDef groupDef;
		groupDef.flags = flags;
		groupDef.groupFlags = groupFlags;
		groupDef.position.Set( positionX, positionY );
		groupDef.angle = angle;
		groupDef.linearVelocity.Set( linVelocityX, linVelocityY );
		groupDef.angularVelocity = angularVelocity;
		groupDef.color.Set( colorR, colorG, colorB, colorA );
		groupDef.strength = strength;
		groupDef.shape = (b2Shape*) shapeAddr;
		groupDef.stride = stride;
		groupDef.particleCount = particleCount;
		groupDef.lifetime = lifetime;
		
		if(positionDataSet) {
			const b2Vec2 positionData = b2Vec2(positionDataX, positionDataY);
			groupDef.positionData = &positionData;
		}
		
		if(groupAddr != -1) {
			groupDef.group = (b2ParticleGroup*)groupAddr;
		}
		
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jlong)system->CreateParticleGroup( groupDef );
	*/
	
	/** Join two particle groups. This function is locked during callbacks.
	 * @param groupA first group. Expands to encompass the second group.
	 * @param groupB second group. It is destroyed. */
	public void joinParticleGroups(ParticleGroup groupA, ParticleGroup groupB) {
		jniJoinParticleGroups(addr, groupA.addr, groupB.addr);
	}
	
	private native void jniJoinParticleGroups(long addr, long addrA, long addrB); /*
		b2ParticleGroup* groupA = (b2ParticleGroup*)addrA;
		b2ParticleGroup* groupB = (b2ParticleGroup*)addrB;
	
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->JoinParticleGroups( groupA, groupB );
	*/
	
	private final static Array<Vector2> mPositions = new Array<Vector2>();
	
	/** Reloads the positionbuffer from native code and returns it */
	public Array<Vector2> getParticlePositionBuffer() {
		updateParticlePositionBuffer();
		return mPositions;
	}
	
	public float[] getParticlePositionBufferX() {
		return jniGetParticlePositionBufferX(addr);
	}
	
	public float[] getParticlePositionBufferY() {
		return jniGetParticlePositionBufferY(addr);
	}
	
	private native float[] jniGetParticlePositionBufferX(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetPositionBuffer()[i].x;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
 		return array;
	*/
	private native float[] jniGetParticlePositionBufferY(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count =  system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetPositionBuffer()[i].y;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
		return array;
	*/
	
	private float[] positionBufferArray = new float[0];
	
	public float[] getParticlePositionBufferArray(boolean update) {
		if(!update) return positionBufferArray;
		int particleCount = getParticleCount();
		
		if(positionBufferArray.length != particleCount * 2) {
			positionBufferArray = new float[particleCount * 2];
		}
		
		jniUpdateParticlePositionBuffer(addr, positionBufferArray);
		return positionBufferArray;
	}
	
	private native void jniUpdateParticlePositionBuffer(long addr, float[] buffer); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		for(int i = 0; i < count * 2; i += 2) {
			buffer[i] = system->GetPositionBuffer()[i / 2].x;
			buffer[i + 1] = system->GetPositionBuffer()[i / 2].y;
		}
	*/
	
	public long getParticlePositionBufferAddress() {
		return jniGetParticlePositionBufferAddress(addr);
	}
	
	private native long jniGetParticlePositionBufferAddress(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jlong) &system->GetPositionBuffer()->x;
	*/
	
	private float[] positionAndColorBufferArray = new float[0];
	
	public float[] getParticlePositionAndColorBufferArray(boolean update) {
		if(!update) return positionAndColorBufferArray;
		int particleCount = getParticleCount();
		
		if(positionAndColorBufferArray.length != particleCount * 6) {
			positionAndColorBufferArray = new float[particleCount * 6];
		}
		
		jniUpdateParticlePositionAndColorBuffer(addr, positionAndColorBufferArray);
		return positionAndColorBufferArray;
	}
	
	private native void jniUpdateParticlePositionAndColorBuffer(long addr, float[] buffer); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		for(int i = 0; i < count; i++) {
			buffer[i * 6] = system->GetPositionBuffer()[i].x;
			buffer[i * 6 + 1] = system->GetPositionBuffer()[i].y;
			buffer[i * 6 + 2] = system->GetColorBuffer()[i].r / 255.0;
			buffer[i * 6 + 3] = system->GetColorBuffer()[i].g / 255.0;
			buffer[i * 6 + 4] = system->GetColorBuffer()[i].b / 255.0;
			buffer[i * 6 + 5] = system->GetColorBuffer()[i].a / 255.0;
		}
	*/
	
	private final static Array<Vector2> mVelocities = new Array<Vector2>();
	
	/** Reloads the velocitybuffer from native code and returns it */
	public Array<Vector2> getParticleVelocityBuffer() {
		updateParticleVelocitiyBuffer();
		return mVelocities;
	}
	
	private native float[] jniGetParticleVelocityBufferX(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetVelocityBuffer()[i].x;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
 		return array;
	*/
	private native float[] jniGetParticleVelocityBufferY(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetVelocityBuffer()[i].y;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
		return array;
	*/
	
	private final static Array<Color> mColors = new Array<Color>();
	
	/** Reloads the colorbuffer from native code and returns it */
	public Array<Color> getParticleColorBuffer() {
		updateParticleColorBuffer();
		return mColors;
	}
	
	private native int[] jniGetParticleColorBufferR(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].r;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	*/
	
	private native int[] jniGetParticleColorBufferG(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].g;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	*/
	
	private native int[] jniGetParticleColorBufferB(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].b;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	*/
	
	private native int[] jniGetParticleColorBufferA(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].a;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	*/
	
	/** Reloads the positionbuffer from native code */
	public void updateParticlePositionBuffer() {
		mPositions.ensureCapacity(getParticleCount());
		mPositions.clear();
		
		float[] x = jniGetParticlePositionBufferX(addr);
		float[] y = jniGetParticlePositionBufferY(addr);
		
		for(int i = 0; i < getParticleCount(); i++) {
			mPositions.add(new Vector2(x[i], y[i]));
		}
	}
	
	/** Reloads the velocitybuffer from native code */
	public void updateParticleVelocitiyBuffer() {
		mVelocities.ensureCapacity(getParticleCount());
		mVelocities.clear();
		
		float[] x = jniGetParticleVelocityBufferX(addr);
		float[] y = jniGetParticleVelocityBufferY(addr);
		
		for(int i = 0; i < getParticleCount(); i++) {
			mVelocities.add(new Vector2(x[i], y[i]));
		}
	}
	
	/** Reloads the colorbuffer from native code */
	public void updateParticleColorBuffer() {
		mColors.ensureCapacity(getParticleCount());
		mColors.clear();
		
		int[] r = jniGetParticleColorBufferR(addr);
		int[] g = jniGetParticleColorBufferG(addr);
		int[] b = jniGetParticleColorBufferB(addr);
		int[] a = jniGetParticleColorBufferA(addr);
		
		for(int i = 0; i < getParticleCount(); i++) {
			mColors.add(new Color(r[i] / 255f, g[i] / 255f, b[i] / 255f, a[i] / 255f));
		}
	}
	
	/** Reloads all buffers from native code */
	public void updateAllParticleBuffers() {
		updateParticlePositionBuffer();
		updateParticleVelocitiyBuffer();
		updateParticleColorBuffer();
	}
	
	/** returns the positionbuffer without reloading it */
	public Array<Vector2> getParticlePositionBufferWithoutUpdate() {
		return mPositions;
	}
	
	/** returns the velocitybuffer without reloading it */
	public Array<Vector2> getParticleVelocityBufferWithoutUpdate() {
		return mVelocities;
	}
	
	/** returns the colorbuffer without reloading it */
	public Array<Color> getParticleColorBufferWithoutUpdate() {
		return mColors;
	}
	
	public int calculateReasonableParticleIterations(float timeStep) {
		return jniCalculateReasonableParticleIterations(world.getAddress(), timeStep);
	}
	
	private native int jniCalculateReasonableParticleIterations(long worldAddr, float timeStep); /*
		b2World* world = (b2World*)worldAddr;
		return (jint)world->CalculateReasonableParticleIterations(timeStep);
	*/
	
	public void setParticleRadius(float radius) {
		jniSetParticleRadius(addr, radius);
	}
	
	private native void jniSetParticleRadius(long addr, float radius); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetRadius( radius );
	*/
	
	public float getParticleRadius() {
		return jniGetParticleRadius(addr);
	}
	
	private native float jniGetParticleRadius(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jfloat)system->GetRadius();
	*/
	
	/** The total count of particles currently in the simulation */
	public int getParticleCount() {
		return jniGetParticleCount(addr);
	}
	
	private native int jniGetParticleCount(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->GetParticleCount();
	*/
	
	public int getParticleGroupCount() {
		return jniGetParticleGroupCount(addr);
	}
	
	private native int jniGetParticleGroupCount(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->GetParticleGroupCount();
	*/
	
	/** Pause or unpause the particle system. When paused, b2World::Step()
	* skips over this particle system. All b2ParticleSystem function calls
	* still work.
	* @param paused is true to pause, false to un-pause. */
	public void setPaused(boolean paused) {
		jniSetPaused(addr, paused);
	}
	
	private native void jniSetPaused(long addr, boolean paused); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetPaused(paused);
	*/
	
	public boolean getPaused() {
		return jniGetPaused(addr);
	}
	
	private native boolean jniGetPaused(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jboolean)system->GetPaused();
	*/
	
	public void setParticleDensity(float density) {
		jniSetParticleDensity(addr, density);
	}
	
	private native void jniSetParticleDensity(long addr, float density); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetDensity(density);
	*/
	
	public float getParticleDensity() {
		return jniGetParticleDensity(addr);
	}
	
	private native float jniGetParticleDensity(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jfloat)system->GetDensity();
	*/
	
	public void setParticleGravityScale(float gravityScale) {
		jniSetParticleGravityScale(addr, gravityScale);
	}
	
	private native void jniSetParticleGravityScale(long addr, float gravityScale); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetGravityScale(gravityScale);
	*/
	
	public float getParticleGravityScale() {
		return jniGetParticleGravityScale(addr);
	}
	
	private native float jniGetParticleGravityScale(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jfloat)system->GetGravityScale();
	*/
	
	public void setParticleMaxCount(int count) {
		jniSetParticleMaxCount(addr, count);
	}
	
	private native void jniSetParticleMaxCount(long addr, float count); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetMaxParticleCount(count);
	*/
	
	public float getMaxParticleCount() {
		return jniGetMaxParticleCount(addr);
	}
	
	private native float jniGetMaxParticleCount(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->GetMaxParticleCount();
	*/
	
	public void setParticleDamping(float damping) {
		jniSetParticleDamping(addr, damping);
	}
	
	private native void jniSetParticleDamping(long addr, float damping); /*
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetDamping(damping);
	*/
	
	public float getParticleDamping() {
		return jniGetParticleDamping(addr);
	}
	
	private native float jniGetParticleDamping(long addr); /*
		b2ParticleSystem* system = (b2ParticleSystem*) addr;
		return (jfloat)system->GetDamping();
	*/
	
	public String getVersionString() {
		return jniGetVersionString(world.getAddress());
	}

	private native String jniGetVersionString(long worldAddr); /*
		b2World* world = (b2World*)worldAddr;
		const char* version = world->GetVersionString();
		return env->NewStringUTF(version);
	*/
}
