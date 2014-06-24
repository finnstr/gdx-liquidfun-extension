
package finnstr.libgdx.liquidfun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

/** Renders all particles from a given {@link ParticleSystem}
 * @author FinnStr */
public class ParticleDebugRenderer {

	protected ShaderProgram mShader;
	protected Mesh mMesh;

	private int attributeLocation;
	
	public ParticleDebugRenderer(Color pColor, int pMaxParticleNumber) {
		mShader = createShader(pColor);
		mMesh = new Mesh(false, pMaxParticleNumber, pMaxParticleNumber, new VertexAttribute(Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE));
	
		attributeLocation = mShader.getAttributeLocation("a_position");
	}

	public void setMaxParticleNumber(int pCount) {
		mMesh.dispose();
		mMesh = new Mesh(false, pCount, pCount, new VertexAttribute(Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE));
	}
	
	public int getMaxParticleNumber() {
		return mMesh.getMaxVertices();
	}
	
	public void render (ParticleSystem pSystem, float pRadiusScale, Matrix4 pProjMatrix) {
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
		Gdx.gl20.glEnable(GL20.GL_VERTEX_PROGRAM_POINT_SIZE);
		Gdx.gl20.glEnable(0x8861); //GL11.GL_POINT_SPRITE_OES
		
		mShader.begin();
		mShader.setUniformf("particlesize", pSystem.getParticleRadius());
		mShader.setUniformf("scale", pRadiusScale);
		mShader.setUniformMatrix("u_projTrans", pProjMatrix);
		
		mMesh.setVertices(pSystem.getParticlePositionBufferArray(true));
		mMesh.render(mShader, GL20.GL_POINTS, 0, pSystem.getParticleCount());
		mShader.end();
		Gdx.gl20.glDisable(0x8861);
	}
	
	public void dispose() {
		mShader.dispose();
		mMesh.dispose();
	}
	
	static final public ShaderProgram createShader(Color pColor) {
		final String vertexShader = 
				"attribute vec4 a_position;\n" //
				+ "\n" //
				+ "uniform float particlesize;\n" //
				+ "uniform float scale;\n"
				+ "uniform mat4 u_projTrans;\n" //
				+ "\n" //
				+ "void main()\n" //
				+ "{\n" //
				+ "   gl_Position =  u_projTrans * vec4(a_position.xy, 0.0, 1.0);\n" //
				+ "   gl_PointSize = scale * particlesize;\n" //
				+ "}\n";
		final String fragmentShader = "#ifdef GL_ES\n" //
		      + "#define LOWP lowp\n" //
		      + "precision mediump float;\n" //
		      + "#else\n" //
		      + "#define LOWP \n" //
		      + "#endif\n" //
				+ "void main()\n"//
				+ "{\n" //
				+ " float len = length(vec2(gl_PointCoord.x - 0.5, gl_PointCoord.y - 0.5));\n" //
				+ " if(len <= 0.5) {\n" //
				+ " 	gl_FragColor = vec4(" + pColor.r + "," + pColor.g + "," + pColor.b + "," + pColor.a + ");\n" //
				+ " } else {\n" //
				+ " 	gl_FragColor = vec4(0, 0, 0, 0);\n" //
				+ " }\n" //
				+ "}";
		
		ShaderProgram shader = new ShaderProgram(vertexShader,
				fragmentShader);
		if (shader.isCompiled() == false) {
			Gdx.app.log("ERROR", shader.getLog());
		}

		return shader;
	}
}
